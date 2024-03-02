# 
# Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
# Licensed under the GNU General Public 3.0 License
#

import dearpygui.dearpygui as dpg
import globals
from globals import ind, inc_ind, dec_ind, Serializable
from abc import ABC, abstractmethod
from pprint import pprint


class NodeAttribute(Serializable):
    def __init__(self):
        from LuaNodes import LuaNode

        self.parent_node: LuaNode = None
        self.id = None
        self.stage = None

    def generate_code(self):
        return ""

    def serialize(self):
        return {
            "id": self.id
        }

    def deserialize(self, obj):
        pass


class NodeAttributeVariable(NodeAttribute):
    def __init__(self):
        super().__init__()
        self.multiline = False

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Output) as self.id:
                self.name = dpg.add_input_text(hint="Variable name", width=150)
                self.value = dpg.add_input_text(hint="Value", width=150)
                self.checkbox = dpg.add_checkbox(label="Multiline", callback=self.checked)

    def checked(self):
        self.multiline = not self.multiline
        dpg.configure_item(self.value, multiline=self.multiline)

    def generate_code(self):
        return f"{dpg.get_value(self.name)}"

    def serialize(self):
        return {
            **super().serialize(),
            "name": dpg.get_value(self.name),
            "value": dpg.get_value(self.value),
            "multiline": self.multiline
        }

    def deserialize(self, obj):
        dpg.configure_item(self.name, default_value=obj["name"])
        dpg.configure_item(self.value, default_value=obj["value"])
        self.multiline = obj["multiline"]
        dpg.configure_item(self.value, multiline=self.multiline)
        dpg.set_value(self.checkbox, self.multiline)


class NodeAttributeMultipleExpressionOut(NodeAttribute):
    def __init__(self, name):
        super().__init__()
        self.attribute_type = NodeAttributeExpressionOut
        self.name = name
        self.value = None
        self.invisible_static = None
        self.arguments = []

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Static) as self.id:
                with dpg.group(horizontal=True):
                    self.name = dpg.add_text(name)
                    dpg.add_button(label="+", callback=lambda: self.add_argument())
                    dpg.add_button(label="-", callback=self.remove_argument)
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Static) as self.empty_static:
                pass

    def add_argument(self, hint=None, name=None):
        arg_name_hint = f"arg{len(self.arguments) + 1}" if hint is None else hint
        # new_arg: NodeAttributeExpressionOut = NodeAttributeExpressionOut(arg_name, simple=True)
        new_arg = self.attribute_type(arg_name_hint, simple=True, value=name)
        self.arguments.append(new_arg)
        dpg.move_item(new_arg.id, parent=self.parent_node.id, before=self.empty_static)
        dpg.delete_item(new_arg.stage)
        # TODO order matters for deserialization?
        self.parent_node.node_attributes.append(new_arg)
        # dpg.move_item(node_attribute.id, parent=self.id)

    def remove_argument(self):
        if len(self.arguments) == 0:
            return
        last_arg = self.arguments.pop()
        # TODO IMPORTANT check if attribute has connections and if so delete them
        dpg.delete_item(last_arg.id)

    def generate_code(self):
        return f"{dpg.get_value(self.name)}"

    def serialize(self):
        return {
            **super().serialize(),
            "name": dpg.get_value(self.name),
            "arguments": [arg.serialize() for arg in self.arguments]
        }

    def deserialize(self, obj):
        dpg.configure_item(self.name, default_value=obj["name"])
        for arg in obj["arguments"]:
            # self.add_argument(arg["value"])
            self.add_argument(name=arg["value"])


class NodeAttributeMultipleExpressionIn(NodeAttributeMultipleExpressionOut):
    def __init__(self, name):
        super().__init__(name)
        self.attribute_type = NodeAttributeExpressionIn


class NodeAttributeMultipleTableEntry(NodeAttribute):
    def __init__(self):
        super().__init__()
        self.entries = []

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Static) as self.id:
                with dpg.group(horizontal=True):
                    self.name = dpg.add_text("Entries")
                    dpg.add_button(label="+", callback=lambda: self.add_argument())
                    dpg.add_button(label="-", callback=self.remove_argument)
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Static) as self.empty_static:
                pass

    def add_argument(self, name=None, value=None):
        print(name, value)
        entry_name_hint = f"name{len(self.entries) + 1}" if name is None else name
        entry_value_hint = f"value{len(self.entries) + 1}" if value is None else value
        # new_arg: NodeAttributeExpressionOut = NodeAttributeExpressionOut(arg_name, simple=True)
        new_entry = NodeAttributeTableEntry(entry_name_hint, entry_value_hint)
        self.entries.append(new_entry)
        dpg.move_item(new_entry.name.id, parent=self.parent_node.id, before=self.empty_static)
        dpg.move_item(new_entry.value.id, parent=self.parent_node.id, before=self.empty_static)
        dpg.delete_item(new_entry.name.stage)
        dpg.delete_item(new_entry.value.stage)
        # TODO order matters for deserialization?
        self.parent_node.node_attributes.append(new_entry.name)
        self.parent_node.node_attributes.append(new_entry.value)

    def remove_argument(self):
        if len(self.entries) == 0:
            return
        last_arg = self.entries.pop()
        # TODO IMPORTANT check if attribute has connections and if so delete them
        dpg.delete_item(last_arg.name.id)
        dpg.delete_item(last_arg.value.id)

    def generate_code(self):
        table_value_code = f"{{\n"
        inc_ind()
        for entry in self.entries:
            table_value_code += f"{ind()}{entry.generate_code()},\n"
        dec_ind()
        table_value_code += f"{ind()}}}"
        return table_value_code

    def serialize(self):

        return {
            **super().serialize(),
            "entries": [arg.serialize() for arg in self.entries]
        }

    def deserialize(self, obj):
        for arg in obj["entries"]:
            self.add_argument(arg["name"]["value"], arg["value"]["value"])


class NodeAttributeTableEntry(NodeAttribute):
    def __init__(self, name, value):
        super().__init__()
        self.name = NodeAttributeExpressionOut(name, simple=True)
        self.value = NodeAttributeExpressionIn(value)

    def generate_code(self):
        name_code = self.name.generate_code()
        value_code = self.value.generate_code()
        if name_code != "":
            return f"{name_code} = {value_code}"
        return value_code

    def serialize(self):
        return {
            **super().serialize(),
            "name": self.name.serialize(),
            "value": self.value.serialize()
        }

    def deserialize(self, obj):
        dpg.set_value(self.name.value, obj["name"])
        dpg.set_value(self.value.value, obj["value"])


class NodeAttributeExpressionOut(NodeAttribute):
    def __init__(self, hint="", simple=False, value=None):
        super().__init__()
        self.value = None
        self.simple = simple

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Output) as self.id:
                if simple:
                    self.value = dpg.add_input_text(hint=hint, width=150)
                    if value:
                        dpg.set_value(self.value, value)
                else:
                    dpg.add_text(hint)

    def generate_code(self):
        # if not simple, we let the parent node decide what the code is for this
        if not self.simple:
            if self.parent_node:
                return self.parent_node.generate_code()
            return ""

        return dpg.get_value(self.value)

    def serialize(self):
        return {
            **super().serialize(),
            "value": dpg.get_value(self.value)
        }

    def deserialize(self, obj):
        if obj["value"]:
            dpg.configure_item(self.value, default_value=obj["value"])


class NodeAttributeExpressionIn(NodeAttribute):
    def __init__(self, hint="", simple=None, value=None):
        super().__init__()
        self.value = None

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Input) as self.id:
                self.value = dpg.add_input_text(hint=hint, width=150)
                if value:
                    dpg.set_value(self.value, value)

    def generate_code(self):
        # need to find the nodeAttribute object this is referring to
        expression_out = globals.get_out_node_attribute_from_in_node_attribute(self.id)
        if expression_out:
            return expression_out.generate_code()
        return dpg.get_value(self.value)

    def serialize(self):
        return {
            **super().serialize(),
            "value": dpg.get_value(self.value)
        }

    def deserialize(self, obj):
        dpg.configure_item(self.value, default_value=obj["value"])


class NodeAttributeStaticText(NodeAttribute):
    def __init__(self, name):
        super().__init__()
        self.value = None

        with dpg.stage() as self.stage:
            with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Static) as self.id:
                self.value = dpg.add_text(name)


class NodeAttributeStaticInputText(NodeAttribute):
    def __init__(self, name):
        super().__init__()
        self.value = None

        with dpg.stage() as self.stage:
            with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Static) as self.id:
                self.value = dpg.add_input_text(hint=name, width=150)

    def generate_code(self):
        return dpg.get_value(self.value)

    def serialize(self):
        return {
            **super().serialize(),
            "value": dpg.get_value(self.value)
        }

    def deserialize(self, obj):
        dpg.configure_item(self.value, default_value=obj["value"])


class NodeAttributeExecuteIn(NodeAttribute):
    def __init__(self, text="Flow in"):
        super().__init__()

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_Triangle,
                                    attribute_type=dpg.mvNode_Attr_Input) as self.id:
                dpg.add_text(text)

    def generate_code(self):
        # execute parent node code
        return self.parent_node.generate_code()

    def serialize(self):
        return {
            **super().serialize(),
        }

    def deserialize(self, obj):
        pass


class NodeAttributeExecuteOut(NodeAttribute):
    def __init__(self, text="Flow out"):
        super().__init__()

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_Triangle, attribute_type=dpg.mvNode_Attr_Output,
                                    indent=100) as self.id:
                dpg.add_text(text)

    def generate_code(self):
        expression_in = globals.get_in_node_attribute_from_out_node_attribute(self.id)
        if expression_in:
            return expression_in.generate_code()
        return ""

    def serialize(self):
        return {
            **super().serialize(),
        }

    def deserialize(self, obj):
        pass


class NodeAttributeCheckbox(NodeAttribute):
    def __init__(self, name):
        super().__init__()
        self.checkbox = None

        with dpg.stage() as self.stage:
            with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Static) as self.id:
                self.checkbox = dpg.add_checkbox(label=name)

    def serialize(self):
        return {
            **super().serialize(),
            "value": dpg.get_value(self.checkbox)
        }

    def deserialize(self, obj):
        dpg.configure_item(self.checkbox, default_value=obj["value"])


class NodeAttributeInlineDeclarationOut(NodeAttribute):
    def __init__(self):
        super().__init__()

        with dpg.stage() as self.stage:
            with dpg.node_attribute(attribute_type=dpg.mvNode_Attr_Output) as self.id:
                dpg.add_text("Inline declaration")

    def generate_code(self):
        return self.parent_node.generate_code(True)


class NodeAttributeCustomCallerOut(NodeAttribute):
    def __init__(self, name="Value", callback=None, params=None):
        super().__init__()
        self.name = name
        self.callback = callback
        self.params = params

        with dpg.stage() as self.stage:
            with dpg.node_attribute(shape=dpg.mvNode_PinShape_CircleFilled,
                                    attribute_type=dpg.mvNode_Attr_Output) as self.id:
                dpg.add_text(name)

    def generate_code(self):
        return self.callback(self.params)
