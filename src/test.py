# import dearpygui.dearpygui as dpg
#
# dpg.create_context()
# dpg.create_viewport(title=f"Test - {dpg.get_dearpygui_version()}", width=500, height=400)
#
# dpg.setup_dearpygui()
#
# with dpg.window(pos=(0, 30), width=500, height=350):
#     dpg.add_button(label="Drag me!")
#     with dpg.drag_payload(parent=dpg.last_item()):
#         dpg.add_text("A new node")
#
#     node_editor = dpg.generate_uuid()
#
#     def on_drop():
#         pos = dpg.get_mouse_pos(local=False)
#         ref_node = dpg.get_item_children(node_editor, slot=1)[0]
#         ref_screen_pos = dpg.get_item_rect_min(ref_node)
#         ref_grid_pos = dpg.get_item_pos(ref_node)
#
#         NODE_PADDING = (8, 8)
#
#         pos[0] = pos[0] - (ref_screen_pos[0] - NODE_PADDING[0]) + ref_grid_pos[0]
#         pos[1] = pos[1] - (ref_screen_pos[1] - NODE_PADDING[1]) + ref_grid_pos[1]
#
#         with dpg.node(label="New", pos=pos, parent=node_editor):
#             with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Static):
#                 dpg.add_text(f"I'm a new node")
#                 dpg.add_text(f"at {pos}!")
#
#     with dpg.group(drop_callback=on_drop):
#         with dpg.node_editor(tag=node_editor,
#                              minimap=True,
#                              minimap_location=dpg.mvNodeMiniMap_Location_BottomRight):
#
#             with dpg.node(label="A real node", pos=[50, 30]):
#                 with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Static):
#                     pass
#
#
# dpg.show_viewport()
# dpg.start_dearpygui()
# dpg.destroy_context()



# multiple_instructions_lambda = lambda x: (
#     print("Executing instruction 1..."),
#     print("The value of x is:", x),
#     print("Executing instruction 2..."),
#     x * 2
# )
#
# result = multiple_instructions_lambda(5)
# print("Result:", result)
#


import dearpygui.dearpygui as dpg
import random
import math


def create_drawing_layers():
    dpg.add_viewport_drawlist(tag='layer_00', front=True)
    dpg.add_viewport_drawlist(tag='layer_01', front=True)
    dpg.add_viewport_drawlist(tag='layer_02', front=True)
    dpg.add_viewport_drawlist(tag='layer_03', front=True)
    dpg.add_viewport_drawlist(tag='layer_04', front=True)
    dpg.add_viewport_drawlist(tag='layer_05', front=True)
    dpg.add_viewport_drawlist(tag='layer_06', front=True)
    dpg.add_viewport_drawlist(tag='layer_07', front=True)
    dpg.add_viewport_drawlist(tag='layer_08', front=True)
    dpg.add_viewport_drawlist(tag='layer_09', front=True)
    dpg.add_viewport_drawlist(tag='layer_10', front=True)
    dpg.add_viewport_drawlist(tag='layer_11', front=True)


def load_texture(file):
    with dpg.texture_registry():
        image_width, image_height, image_channels, image_buffer = dpg.load_image(file)
        return dpg.add_static_texture(image_width, image_height, image_buffer)


def make_image(file, x_pos, y_pos, layer):
    with dpg.texture_registry():
        image_width, image_height, image_channels, image_buffer = dpg.load_image(file)
        texture = dpg.add_static_texture(image_width, image_height, image_buffer)
        dpg.draw_image(texture_tag=texture, pmin=(x_pos, y_pos), pmax=(x_pos + image_width, y_pos + image_height),
                       parent=layer)


def create_scene():
    make_image('assets/scene/trunk.png', 80, 530, 'layer_01')
    make_image('assets/scene/rock_4.png', 510, 540, 'layer_03')
    make_image('assets/scene/rock_2.png', -30, 510, 'layer_03')
    make_image('assets/scene/grass.png', 0, 495, 'layer_05')
    make_image('assets/scene/rock_6.png', 400, 590, 'layer_05')
    make_image('assets/scene/rock_3.png', 200, 610, 'layer_05')
    make_image('assets/flame/logs_small.png', 280, 615, 'layer_07')
    make_image('assets/scene/rock_1.png', 530, 600, 'layer_09')
    make_image('assets/scene/rock_5.png', 400, 620, 'layer_09')
    make_image('assets/scene/foreground.png', 0, 605, 'layer_10')


class Raccoon:

    def __init__(self, x, y, start_frame, direction, textures, image_width, image_height, face_direction,
                 flip_face_setting, left_boundary, right_boundary, boundary_variation, layer):
        self.x = x
        self.y = y + 200 - image_height
        self.x_start = x
        self.direction = direction
        self.flip_face_setting = flip_face_setting
        self.left_boundary = left_boundary
        self.right_boundary = right_boundary
        self.boundary_variation = boundary_variation
        self.temp_boundary = 1
        self.face_direction = face_direction
        self.layer = layer
        self.frame = start_frame
        self.num_frames = 14
        self.textures = textures
        self.image_width = image_width
        self.image_height = image_height
        if face_direction == 'right':
            self.raccoon = dpg.draw_image(texture_tag=self.textures['dance_right'][start_frame], pmin=(self.x, self.y),
                                          pmax=(self.image_width + self.x, self.image_height + self.y),
                                          parent=self.layer)
        else:
            self.raccoon = dpg.draw_image(texture_tag=self.textures['dance_left'][start_frame], pmin=(self.x, self.y),
                                          pmax=(self.image_width + self.x, self.image_height + self.y),
                                          parent=self.layer)

    def flip_face_direction(self):
        self.face_direction = 'right' if self.face_direction == 'left' else 'left'

    def dance(self):
        if self.frame < self.num_frames - 1:
            self.frame += 1
        else:
            self.frame = 0
        self.x += self.direction
        if (self.x > self.x_start + self.right_boundary + self.temp_boundary) or \
                (self.x < self.x_start - self.left_boundary - self.temp_boundary):
            self.direction = -self.direction
            self.temp_boundary = random.randint(0, self.boundary_variation)
            if self.flip_face_setting:
                self.flip_face_direction()
        if self.face_direction == 'right':
            dpg.configure_item(self.raccoon, texture_tag=self.textures['dance_right'][self.frame],
                               pmin=(self.x, self.y), pmax=(self.image_width + self.x, self.image_height + self.y))
        else:
            dpg.configure_item(self.raccoon, texture_tag=self.textures['dance_left'][self.frame], pmin=(self.x, self.y),
                               pmax=(self.image_width + self.x, self.image_height + self.y))

    def idle(self):
        if self.frame < 11 - 1:
            self.frame += 1
        else:
            self.frame = 0
        if self.face_direction == 'right':
            dpg.configure_item(self.raccoon, texture_tag=self.textures['idle_right'][self.frame], pmin=(self.x, self.y),
                               pmax=(self.image_width + self.x, self.image_height + self.y))
        else:
            dpg.configure_item(self.raccoon, texture_tag=self.textures['idle_left'][self.frame], pmin=(self.x, self.y),
                               pmax=(self.image_width + self.x, self.image_height + self.y))


class Animation:

    def __init__(self, x, y, num_frames, textures, image_width, image_height, max_variation, layer):
        self.x = x
        self.y = y
        self.frame = 0
        self.num_frames = num_frames
        self.textures = textures
        self.image_width = image_width
        self.image_height = image_height
        self.max_variation = max_variation
        self.variation_step_speed = 5
        self.variation_current_step = 0
        self.current_variation = 0
        self.decrease_size = True
        self.layer = layer
        self.sprite = dpg.draw_image(texture_tag=self.textures[0], pmin=(self.x, self.y),
                                     pmax=(image_width + self.x, image_height + self.y), parent=self.layer)

    def update(self):
        if self.frame < self.num_frames - 1:
            self.frame += 1
        else:
            self.frame = 0

        if self.max_variation != 0:

            if self.variation_current_step >= self.variation_step_speed:

                self.variation_current_step = 0

                if self.decrease_size:
                    self.current_variation += 1  # steps have to be integers to avoid visual artefacts
                else:
                    self.current_variation -= 1

                if self.current_variation > self.max_variation:
                    self.decrease_size = False
                elif self.current_variation < 0:
                    self.decrease_size = True
            else:
                self.variation_current_step += 1

        dpg.configure_item(self.sprite, texture_tag=self.textures[self.frame],
                           pmin=(self.x, self.y - self.current_variation),
                           pmax=(self.image_width + self.x, self.image_height + self.y))


class Particle:

    def __init__(self, x, y, horizontal_speed, starting_frame, num_frames, interval, textures, image_width,
                 image_height, layer):
        self.step = 1
        self.x = x
        self.horizontal_speed = horizontal_speed
        self.y_base = y
        self.sin_variation = random.randint(0, 628) / 100
        self.y = y
        self.vertical_trend = 1
        self.frame = starting_frame
        self.num_frames = num_frames
        self.interval = interval
        self.textures = textures
        self.image_width = image_width
        self.image_height = image_height
        self.layer = layer
        self.sprite = dpg.draw_image(texture_tag=self.textures[0], pmin=(self.x, self.y),
                                     pmax=(image_width + self.x, image_height + self.y), parent=self.layer)

    def reset(self):
        self.x = -50 - random.randint(0, 1000)
        self.y = 445 + random.randint(0, 50) + self.x / 10
        self.vertical_trend = 1
        self.y_base = self.y
        self.step = 1

    def update(self):
        self.interval += 1  # interval determines how many update calls it takes to update a frame
        if self.interval == 60:
            self.interval = 0
            if self.frame < self.num_frames - 1:
                self.frame += 1
            else:
                self.frame = 0
        self.step += 0.00001
        self.x = self.x + self.horizontal_speed + math.sin(self.step) / 100
        self.y_base = self.y_base + (3.1 / self.vertical_trend)
        # vertical_trend reversely impacts the vertical speed. A smaller number means faster downward speed.
        self.vertical_trend += 0.085
        self.y = self.y_base + math.sin(self.x / 20 + self.sin_variation) * 3
        dpg.configure_item(self.sprite, texture_tag=self.textures[self.frame], pmin=(self.x, self.y),
                           pmax=(self.image_width + self.x, self.image_height + self.y))


def load_logo():
    with dpg.viewport_drawlist(front=True, tag='viewport_front'):

        dpg.draw_text(pos=(213, 4), tag='logo_raccoon', text='Raccoon', size=140, color=(217, 131, 46))
        dpg.bind_item_font(item='logo_raccoon', font='font3')

        dpg.draw_text(pos=(275, 110), tag='logo_musicplayer', text='M U S I C   P L A Y E R', size=21,
                      color=(95, 59, 35))
        dpg.bind_item_font(item='logo_musicplayer', font='font4')
        dpg.draw_rectangle(tag='logo_text_filter', color=(0, 0, 0, 0), fill=(0, 0, 0, 0),
                           pmin=(50, 30), pmax=(510, 150))
        dpg.draw_rectangle(tag='leaves_button_filter', color=(0, 0, 0, 0), fill=(222, 0, 0, 0),
                           pmin=(540, 0), pmax=(630, 70))
        make_image('assets/raccoon/raccoon_logo_face.png', 82, 35, 'layer_01')


def load_textures(filepath, asset_name, image_type, number_of_assets):
    textures = []
    with dpg.texture_registry():
        for frame in range(number_of_assets):
            file = filepath + '/' + asset_name + '_' + str(frame) + '.' + image_type
            image_width, image_height, image_channels, image_buffer = dpg.load_image(file)
            texture = dpg.add_static_texture(image_width, image_height, image_buffer)
            textures.append(texture)
    return textures