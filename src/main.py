# 
# Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
# Licensed under the GNU General Public 3.0 License
#

import os

import dearpygui.dearpygui as dpg
from pprint import pprint
import threading

import themes
from LuaNodes import *
import pyperclip as pc
import json

import tkinter as tk
from PIL import Image, ImageTk

def show_splash_screen():
    splash_root = tk.Tk()
    splash_root.overrideredirect(True)
    screen_width = splash_root.winfo_screenwidth()
    screen_height = splash_root.winfo_screenheight()
    center_x = int((screen_width - 600) / 2)
    center_y = int((screen_height - 200) / 2)
    splash_root.geometry(f"600x200+{center_x}+{center_y}")

    image_path = "splash.png" # Remove Source/ if it cant find the image
    original_image = Image.open(image_path)
    desired_width = 600
    desired_height = 200
    resized_image = original_image.resize((desired_width, desired_height))
    resized_photo = ImageTk.PhotoImage(resized_image)

    resized_label = tk.Label(splash_root, image=resized_photo)
    resized_label.image = resized_photo
    resized_label.pack()

    splash_root.after(3000, splash_root.destroy) 

    splash_root.mainloop()

if __name__ == "__main__":
    show_splash_screen()
    dpg.create_context()
    dpg.configure_app(manual_callback_management=True)
    dpg.configure_app(init_file="settings.ini")
    dpg.create_viewport(title='Lua Node Editor', width=1200, height=800, small_icon="icon.ico", large_icon="icon.ico")

    # Variable list
    hasGeneratingCodeBeenLogged = False
    hasCopyCodeBeenLogged = False
    isCodeGenerated = False

    def save_init():
        dpg.save_init_file("settings.ini")

    def call_threaded(function, args):
        my_thread = threading.Thread(target=function, args=args)
        my_thread.start()

    # callback runs when user attempts to connect attributes
    def link_callback(sender, app_data):
        # check for no other link to have the same end
        for l in globals.links:
            if l.to_attribute == app_data[1]:
                call_threaded(add_log, ("Can't have multiple inputs", ))
                add_log("Can't have multiple inputs")
                return

        id = dpg.add_node_link(app_data[0], app_data[1], parent=sender)

        from_node = dpg.get_item_parent(app_data[0])
        to_node = dpg.get_item_parent(app_data[1])

        globals.links += [Link(id, from_node, to_node, app_data[0], app_data[1])]


    # callback runs when user attempts to disconnect attributes
    def delink_callback(sender, app_data):
        # app_data -> link_id
        dpg.delete_item(app_data)
        globals.links = list(filter(lambda l: l.id != app_data, globals.links))


    def get_mouse_pos_relative_to(item):
        item_min_rect = dpg.get_item_state(item)["rect_min"]
        mouse_pos = dpg.get_mouse_pos(local=False)

        return [mouse_pos[0] - item_min_rect[0], mouse_pos[1] - item_min_rect[1]]


    def get_mouse_pos_in_node_editor():
        ref_node_rect_min = dpg.get_item_state("reference_node")["rect_min"]

        node_editor_rect_min = dpg.get_item_state("node_editor_container")["rect_min"]
        ref_node_rect_min = [ref_node_rect_min[0] - node_editor_rect_min[0], ref_node_rect_min[1] - node_editor_rect_min[1]]

        local_mouse_pos = get_mouse_pos_relative_to("node_editor_container")
        return [local_mouse_pos[0] - ref_node_rect_min[0], local_mouse_pos[1] - ref_node_rect_min[1]]


    def get_starting_node():
        for node in globals.nodes:
            if isinstance(node, LuaStartNode):
                return node
        return None


    def add_log(log_message):
        with dpg.stage() as stage:
            text_added = dpg.add_text(log_message, tracked=True, track_offset=1)

        dpg.push_container_stack("log_container")
        dpg.unstage(stage)
        dpg.pop_container_stack()
        dpg.delete_item(stage)

        dpg.split_frame(delay=1)
        dpg.configure_item(text_added,  tracked=False)

    def show_modal(window_title, modal_message, ok_callback=None, show_cancel=False, cancel_callback=None):
        dpg.delete_item("modal")
        # with dpg.stage() as modal_stage:
            # modal window
        with dpg.window(modal=True, no_move=False, show=True,
                        pos=[9999, 9999],
                        no_resize=True, tag="modal", min_size=[0, 0]):
            dpg.add_text("", tag="modal_text")
            dpg.add_spacer()
            with dpg.group(horizontal=True):
                dpg.add_button(label="Ok", callback=default_modal_callback, tag="modal_ok")
                dpg.add_button(label="Cancel", show=False, tag="modal_cancel")

        dpg.configure_item("modal", label=window_title)
        dpg.configure_item("modal_text", default_value=modal_message)

        dpg.configure_item("modal_cancel", show=show_cancel)
        dpg.configure_item("modal_ok", callback=default_modal_callback if ok_callback is None else ok_callback)

        if show_cancel:
            dpg.configure_item("modal_cancel", callback=cancel_callback)

        def test():
            dpg.split_frame(delay=1)

            w = dpg.get_item_width("modal")
            h = dpg.get_item_height("modal")
            print(w, h)
            dpg.configure_item("modal", pos=[dpg.get_viewport_width() // 2 - w/2, dpg.get_viewport_height() // 2 - h/2])

        call_threaded(test, ())
        # dpg.delete_item(modal_stage)


    def show_help_modal():
        help_text = f'''
    Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
    Licensed under the GNU General Public 3.0 License

    Interface Navigation:
    1. Panning the View:
    Middle Mouse Button: Hold down the middle mouse button and move the mouse to pan the view across the editor canvas.
    2. Selecting Nodes and Links:
    Click and Drag: Select multiple nodes and links by clicking and dragging a selection box around them.
    Clear Selection: Click outside of the selected area to clear the current selection.
    3. Connecting Nodes:
    Drag to Connect: To establish connections between nodes, click on a pin of one node and drag to the pin of another. Release the mouse button to create a connection.
    4. Disconnecting Nodes:
    Control + Drag: To disconnect a pin, hold down the Control key and drag the pin away from its connected node.
    5. Node Creation:
    Right-Click: Open the context menu by right-clicking on the editor window. Select 'Create New Node' to add a new node to your workflow.

    File Management:
    1. Creating a New File:
    Shortcut: Press File + New to create a new file.
    2. Saving Your Work:
    Shortcut: Press File + Save As to save your current work. Specify a file name and location.
    3. Loading a Previous File:
    Shortcut: Press File + Load to open a previously saved file.

    Code Generation:
    1. Generating Code:
    Click 'Generate Code': Once you've designed your node network, click the 'Generate Code' button to automatically generate the corresponding code.
    2. Copy Code:
    Click 'Copy Code': Once you've generated your node network and what to copy the code.
        '''
        show_modal("Help", help_text)

        pass

    def generate_code():
        code = ""
        # add variable declaration code
        # Variables
        global hasGeneratingCodeBeenLogged
        global hasCopyCodeBeenLogged
        global isCodeGenerated
        for node in globals.nodes:
            if isinstance(node, LuaVariableNode) or isinstance(node, LuaTable):
            # if isinstance(node, LuaVariableNode):
                if not node.has_from_node():
                    code += node.generate_code()

        # add global functions code
        for node in globals.nodes:
            if isinstance(node, LuaNodeFunction):
                if not node.has_from_node() and not node.is_inline():
                    code += node.generate_code()

        start_node = get_starting_node()
        if start_node is None:
            show_modal("Warning", "Start node not found! Please add nodes.")
            isCodeGenerated = False
            if not hasGeneratingCodeBeenLogged:
                call_threaded(add_log, ("Please add a starting node for proper code generation",))
                hasGeneratingCodeBeenLogged = True
                hasCopyCodeBeenLogged = False
        else:
            code += start_node.generate_code()
            isCodeGenerated = True

        dpg.configure_item("generated_code", default_value=code)

    def copy_code():
        # Variables
        global hasGeneratingCodeBeenLogged
        global hasCopyCodeBeenLogged
        global isCodeGenerated

        if not isCodeGenerated:
            show_modal("Warning", "Generated code not found! Please generate code.")
            if not hasCopyCodeBeenLogged:
                call_threaded(add_log, ("Please generate code before copying the code.",))
                hasCopyCodeBeenLogged = True
                hasGeneratingCodeBeenLogged = False
        else:
            pc.copy(dpg.get_value("generated_code"))


    def delete_selected_nodes():
        selected_nodes = dpg.get_selected_nodes("node_editor")
        globals.nodes = list(filter(lambda n: n.id not in selected_nodes, globals.nodes))

        # ref_node = dpg.get_item_label(node)
        for node in selected_nodes:
            if dpg.get_item_label(node) == dpg.get_item_label("reference_node"):
                continue
            dpg.delete_item(node)
            # dpg.delete_item(node)

        # delete links that are left hanging
        node_ids = [node.id for node in globals.nodes]
        globals.links = list(filter(lambda l: l.from_node in node_ids and l.to_node in node_ids, globals.links))


    def create_node(node_type):
        node: LuaNode = create_node_of_type(node_type)

        if node:
            node.submit("node_editor")
            dpg.configure_item(node.id, pos=get_mouse_pos_in_node_editor())
            globals.nodes.append(node)
            themes.apply_theme(node)

        dpg.configure_item("menu_create_node", show=False)


    def right_click_callback(sender, app_data, user_data):
        if dpg.is_item_hovered("node_editor"):
            dpg.configure_item("menu_create_node", show=True, pos=dpg.get_mouse_pos(local=False))
            dpg.focus_item("menu_input_create_node")
            dpg.set_value("menu_input_create_node", "")
            dpg.set_value("node_search_filter", "")


    def key_press_callback(s, key):
        if key == dpg.mvKey_Delete:
            delete_selected_nodes()
        elif key == dpg.mvKey_T:
            print(globals.nodes)
            print(globals.links)
            # for child in dpg.get_item_children("node_search_filter", slot=1):
            #     print(dpg.get_item_state(child))

        elif dpg.is_key_down(dpg.mvKey_Control):
            if key == dpg.mvKey_1:
                dpg.show_item_registry()
            if key == dpg.mvKey_2:
                dpg.show_style_editor()
            elif key == dpg.mvKey_S:
                open_save_dialog()
            elif key == dpg.mvKey_L:
                open_load_dialog()

    def open_save_dialog():
        create_folder_if_not_exists("save_files")
        dpg.show_item("save_dialog")

    def open_export_dialog():
        create_folder_if_not_exists("export_files")
        dpg.show_item("export_dialog")

    def open_load_dialog():
        create_folder_if_not_exists("save_files")
        dpg.show_item("load_dialog")

    def create_folder_if_not_exists(folder_path):
        if not os.path.exists(folder_path):
            try:
                os.makedirs(folder_path)
                print(f"Folder '{folder_path}' created successfully.")
            except OSError as e:
                print(f"Error creating folder '{folder_path}': {e}")
        else:
            print(f"Folder '{folder_path}' already exists.")


    def is_editor_empty():
        return globals.links == [] and globals.nodes == []


    def menu_pressed_new_file():
        # already empty, do nothing
        if is_editor_empty():
            return

        def clear_editor_and_hide_modal():
            reset_node_editor()
            default_modal_callback()

        show_modal("You are trying to create a new board", "All nodes will be erased. Continue?",
                ok_callback=clear_editor_and_hide_modal, show_cancel=True, cancel_callback=default_modal_callback)


    def reset_node_editor():
        globals.nodes = []
        globals.links = []
        children = dpg.get_item_children("node_editor", slot=1)
        for child in children:
            # avoid deleting the reference node
            if dpg.get_item_label(child) == dpg.get_item_label("reference_node"):
                continue
            # delete twice cuz it doesnt work otherwise
            dpg.delete_item(child)


    def save(file_path):
        with open(file_path, 'w') as save_file:
            json_obj = {
                "nodes": {},
                "links": {}
            }

            nod: LuaNode
            for node in globals.nodes:
                json_obj["nodes"][node.id] = node.serialize()
            link: Link
            for link in globals.links:
                json_obj["links"][link.id] = link.serialize()

            json_string = json.dumps(json_obj, indent=2)
            save_file.write(json_string)


    def load(file_path):
        # try:
        with open(file_path, 'r') as file:
            # Do something with the file, e.g., read its content
            json_string = file.read()

            data = json.loads(json_string)
            # after conversion to object was successful, reset node editor in order to populate again
            reset_node_editor()

            new_node_data = {}
            for node_id, node_data in data["nodes"].items():
                # try:
                new_node: LuaNode = create_node_of_type(node_data["node_type"])
                new_node.submit("node_editor")

                new_node.deserialize(node_data)
                new_node_data[node_id] = {
                    "new_id": new_node.id,
                    "new_attributes": dict(
                        zip(list(node_data["attributes"].keys()), [attr.id for attr in new_node.node_attributes]))
                }
                globals.nodes.append(new_node)
                themes.apply_theme(new_node)

                # except:
                #     pass

            for link_id, link_data in data["links"].items():
                # try:
                from_node = new_node_data[str(link_data["from_node"])]["new_id"]
                to_node = new_node_data[str(link_data["to_node"])]["new_id"]
                from_attribute = new_node_data[str(link_data["from_node"])]["new_attributes"][
                    str(link_data["from_attribute"])]
                to_attribute = new_node_data[str(link_data["to_node"])]["new_attributes"][str(link_data["to_attribute"])]

                new_link_id = dpg.add_node_link(from_attribute, to_attribute, parent="node_editor")
                new_link = Link(new_link_id, from_node, to_node, from_attribute, to_attribute)

                globals.links.append(new_link)
                # except:
                #     pass

            dpg.clear_selected_nodes("node_editor")
            dpg.clear_selected_links("node_editor")

        # except:
        #     dpg.split_frame(delay=1)
        #     show_modal("Error", "Something went wrong!")


    with dpg.handler_registry():
        dpg.add_mouse_click_handler(button=dpg.mvMouseButton_Right, callback=right_click_callback)
        dpg.add_key_press_handler(callback=key_press_callback)

    with dpg.theme() as reference_node_theme:
        with dpg.theme_component(dpg.mvAll):
            # make it invisible in the node editor
            dpg.add_theme_color(dpg.mvNodeCol_NodeBackground, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_NodeBackgroundHovered, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_NodeBackgroundSelected, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_NodeOutline, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_TitleBar, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_TitleBarHovered, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_TitleBarSelected, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)

            # make it tiny in the node editor
            dpg.add_theme_style(dpg.mvNodeStyleVar_NodePadding, 1, 0, category=dpg.mvThemeCat_Nodes)

    themes.init_themes()

    def load_file_callback(sender, app_data):
        path = list(app_data["selections"].values())[0]
        load(path)


    def save_file_callback(sender, app_data):
        path = None
        if len(app_data["selections"]) == 0:
            print(app_data)
            path = app_data["file_path_name"]
        else:
            path = list(app_data["selections"].values())[0]
        print(path)
        save(path)

    def export_file_callback(sender, app_data):
        path = None
        if len(app_data["selections"]) == 0:
            print(app_data)
            path = app_data["file_path_name"]
        else:
            path = list(app_data["selections"].values())[0]
        print(path)
        save(path)


    def file_dialog_cancel_callback(sender, app_data):
        pass

    with dpg.font_registry():
        # first argument ids the path to the .ttf or .otf file
        bold_font = dpg.add_font("robotoBold.ttf", 18, tag="bold_roboto") # Remove Source\\ if it cant find the Font
        default_font = dpg.add_font("roboto.ttf", 14, tag="roboto")  # Remove Source\\ if it cant find the Font

    # file selector
    # Open Project / Importing
    with dpg.file_dialog(
            directory_selector=False, show=False, callback=load_file_callback, tag="load_dialog",
            cancel_callback=file_dialog_cancel_callback, width=700, height=400, modal=True, default_path="save_files"):
        dpg.add_file_extension(".*")
        dpg.add_file_extension(".lvs", color=(0, 255, 0, 255), custom_text="[Lua Visual Script]")




    # Save as
    with dpg.file_dialog(
            directory_selector=False, show=False, callback=save_file_callback, tag="save_dialog",
            cancel_callback=file_dialog_cancel_callback, width=700, height=400, modal=True, default_path="save_files"):
        dpg.add_file_extension(".*")
        dpg.add_file_extension(".lvs", color=(0, 255, 0, 255), custom_text="[Lua Visual Script]")



    # Export
    with dpg.file_dialog(
            directory_selector=False, show=False, callback=export_file_callback, tag="export_dialog",
            cancel_callback=file_dialog_cancel_callback, width=700, height=400, modal=True, default_path="export_files"):
        dpg.add_file_extension(".lua", color=(0, 0, 138), custom_text="(WIP)[Lua]")


    # create node window popup
    with dpg.window(label="Create node", show=False, tag="menu_create_node", no_title_bar=True, popup=True,
                    no_move=True, max_size=(1000, 200)):
        dpg.add_input_text(hint="Search", tag="menu_input_create_node",
                        callback=lambda s, a: dpg.set_value("node_search_filter", a))
        with dpg.group(horizontal=False):
            with dpg.filter_set(tag="node_search_filter"):
                # dpg.add_text("aaa1.c", filter_key="aaa1.c", bullet=True)
                for node_type, name in lua_ntNames.items():
                    dpg.add_button(label=name, filter_key=str(name), user_data=node_type,
                                callback=lambda s, a, u: create_node(u))

    # main window
    with dpg.window(tag="main_window") as main_win:
        with dpg.menu_bar():
            with dpg.menu(label="File"):
                dpg.add_menu_item(label="Open", callback=lambda: open_load_dialog())
                dpg.add_menu_item(label="New", callback=lambda: menu_pressed_new_file())
                dpg.add_menu_item(label="Save as", callback=lambda: open_save_dialog())
                dpg.add_menu_item(label="Export(WIP)", callback=lambda: open_export_dialog())
                
            with dpg.menu(label="Settings"):
                dpg.add_menu_item(label="Style editor", callback=lambda: dpg.show_style_editor())
                # dpg.add_menu_item(label="Save style", callback=lambda: save_init())
            dpg.add_menu_item(label="Help", callback=lambda: show_help_modal())

        with dpg.group(horizontal=True):
            # with dpg.child_window(width=150):
            #     pass
            with dpg.group(tag="node_editor_container"):
                # with dpg.child_window(tag="test_tag", width=-350):
                with dpg.node_editor(callback=link_callback, delink_callback=delink_callback, minimap=True,
                                    minimap_location=dpg.mvNodeMiniMap_Location_BottomRight,
                                    tag="node_editor", width=-350) as node_editor:
                    with dpg.node(pos=(0, 0), label="", draggable=False, tag="reference_node"):
                        pass

                    # with dpg.node(label="Node 2", tag="node22"):
                    #     with dpg.node_attribute(label="Node A3"):
                    #         dpg.add_input_float(label="F3", width=200)
                    #
                    #     with dpg.node_attribute(label="Node A4", attribute_type=dpg.mvNode_Attr_Output):
                    #         dpg.add_input_float(label="F4", width=200)

            with dpg.group():
                with dpg.group(horizontal=True):
                    # with dpg.child_window(tag="test_tag"):
                    dpg.add_button(label="Generate code", callback=generate_code)
                    dpg.add_button(label="Copy code", callback=lambda: copy_code())
                dpg.add_input_text(height=-200, multiline=True, tag="generated_code", width=350)
                with dpg.tab_bar():
                    with dpg.tab(label="Console"):
                        with dpg.child_window(height=-1, border=False, tag="log_container"):
                            pass
                    with dpg.tab(label="Profiler(WIP)"):
                        with dpg.child_window(height=-1, border=False, tag="profiler_container"):
                            pass

        dpg.bind_font(default_font)

    # dpg.bind_item_handler_registry("node_editor", "node_editor_handler")


    def empty_func():
        pass


    def default_modal_callback():
        dpg.configure_item("modal", show=False)


    # hide reference node on first frame (actually cant do it because it wont have rect_min set properly)
    # dpg.set_frame_callback(frame=1, callback=lambda: dpg.hide_item("reference_node"))
    # so i just hide it instead
    dpg.bind_item_theme("reference_node", reference_node_theme)

    dpg.setup_dearpygui()
    dpg.show_viewport()
    dpg.set_primary_window(main_win, True)

    while dpg.is_dearpygui_running():
        jobs = dpg.get_callback_queue()  # retrieves and clears queue
        dpg.run_callbacks(jobs)
        dpg.render_dearpygui_frame()

    # dpg.start_dearpygui()
    dpg.destroy_context()
