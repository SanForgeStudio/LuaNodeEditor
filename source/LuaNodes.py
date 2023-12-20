# 
# Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
# Licensed under the GNU General Public 3.0 License
#

import dearpygui.dearpygui as dpg
import globals
from globals import ind, inc_ind, dec_ind, Serializable, bcksl
from LuaNodeAttributes import *

lua_ntVariable = 0
lua_ntStart = 1
lua_ntPrint = 2
lua_ntExpression = 3
lua_ntForLoop = 4
lua_ntIf = 5
lua_ntElseIf = 6
lua_ntElse = 7
lua_ntAdd = 8
lua_ntSubtract = 9
lua_ntMultiply = 10
lua_ntDivide = 11
lua_ntGreater = 12
lua_ntGreaterOrEqual = 13
lua_ntLess = 14
lua_ntLessOrEqual = 15
lua_ntEqual = 16
lua_ntNotEqual = 17
lua_ntAnd = 18
lua_ntOr = 19
lua_ntWhileLoop = 20
lua_ntAssign = 21
lua_ntFunctionDeclaration = 22
lua_ntFunctionCall = 23
lua_ntConcat = 24
lua_ntReturn = 25
lua_ntChainExpression = 26
lua_ntIndexTable = 27
lua_ntIndexTableByKey = 28
lua_ntIteratePairs = 29
lua_ntIterateIPairs = 30
lua_ntTable = 31
lua_ntNot = 32
lua_ntModulus = 33

lua_ntNames = {
    lua_ntStart: "Entry",
    lua_ntVariable: "Variable",
    lua_ntTable: "Variable Table",
    lua_ntPrint: "Print",
    lua_ntExpression: "Expression",
    lua_ntForLoop: "For Loop",
    lua_ntWhileLoop: "While Loop",
    lua_ntIf: "If",
    lua_ntElseIf: "Else If",
    lua_ntElse: "Else",
    lua_ntAdd: "Add(+)",
    lua_ntSubtract: "Subtract(-)",
    lua_ntMultiply: "Multiply(*)",
    lua_ntDivide: "Divide(/)",
    lua_ntModulus: "Modulus(%)",
    lua_ntGreater: "Greater(>)",
    lua_ntGreaterOrEqual: "Greater or equal(>=)",
    lua_ntLess: "Less(<)",
    lua_ntLessOrEqual: "Less or equal(<=)",
    lua_ntEqual: "Equal(==)",
    lua_ntNotEqual: "Not equal(~=)",
    lua_ntAnd: "And",
    lua_ntOr: "Or",
    lua_ntNot: "Not",
    lua_ntAssign: "Assign",
    lua_ntFunctionDeclaration: "Function Declaration",
    lua_ntFunctionCall: "Function Call",
    lua_ntConcat: "Concat",
    lua_ntReturn: "Return",
    lua_ntChainExpression: "Chain Expression",
    lua_ntIndexTable: "Index Table",
    lua_ntIndexTableByKey: "Index Table By Key",
    lua_ntIteratePairs: "Iterate Pairs",
    lua_ntIterateIPairs: "Iterate IPairs",

}


def create_node_of_type(type):
    node = None
    if type == lua_ntVariable:
        node = LuaVariableNode()
    if type == lua_ntTable:
        node = LuaTable()
    elif type == lua_ntStart:
        node = LuaStartNode()
    elif type == lua_ntPrint:
        node = LuaPrintNode()
    elif type == lua_ntExpression:
        node = LuaExpressionNode()
    elif type == lua_ntForLoop:
        node = LuaNodeForLoop()
    elif type == lua_ntWhileLoop:
        node = LuaNodeWhileLoop()
    elif type == lua_ntIf:
        node = LuaIfNode()
    elif type == lua_ntElseIf:
        node = LuaElseIfNode()
    elif type == lua_ntElse:
        node = LuaElseNode()
    elif type == lua_ntAdd:
        node = LuaNodeAdd()
    elif type == lua_ntSubtract:
        node = LuaNodeSubtract()
    elif type == lua_ntMultiply:
        node = LuaNodeMultiply()
    elif type == lua_ntDivide:
        node = LuaNodeDivide()
    elif type == lua_ntGreater:
        node = LuaNodeGreater()
    elif type == lua_ntGreaterOrEqual:
        node = LuaNodeGreaterOrEqual()
    elif type == lua_ntLess:
        node = LuaNodeLess()
    elif type == lua_ntLessOrEqual:
        node = LuaNodeLessOrEqual()
    elif type == lua_ntEqual:
        node = LuaNodeEqual()
    elif type == lua_ntNotEqual:
        node = LuaNodeNotEqual()
    elif type == lua_ntAnd:
        node = LuaNodeAnd()
    elif type == lua_ntOr:
        node = LuaNodeOr()
    elif type == lua_ntAssign:
        node = LuaNodeAssign()
    elif type == lua_ntFunctionDeclaration:
        node = LuaNodeFunction()
    elif type == lua_ntFunctionCall:
        node = LuaNodeFunctionCall()
    elif type == lua_ntConcat:
        node = LuaNodeConcat()
    elif type == lua_ntReturn:
        node = LuaNodeReturn()
    elif type == lua_ntChainExpression:
        node = LuaNodeChainExpression()
    elif type == lua_ntIndexTable:
        node = LuaNodeIndexTable()
    elif type == lua_ntIndexTableByKey:
        node = LuaNodeIndexTableByKey()
    elif type == lua_ntIteratePairs:
        node = LuaNodeIteratePairs()
    elif type == lua_ntIterateIPairs:
        node = LuaNodeIterateIPairs()
    elif type == lua_ntNot:
        node = LuaNodeNot()
    elif type == lua_ntModulus:
        node = LuaNodeModulus()

    dpg.bind_item_font(node.id, "bold_roboto")
    return node


class Link(Serializable):
    def __init__(self, id, from_node, to_node, from_attribute, to_attribute):
        self.id = id
        self.from_node = from_node
        self.to_node = to_node
        self.from_attribute = from_attribute
        self.to_attribute = to_attribute

    def serialize(self):
        return {
            "id": self.id,
            "from_node": self.from_node,
            "to_node": self.to_node,
            "from_attribute": self.from_attribute,
            "to_attribute": self.to_attribute
        }

    def deserialize(self, obj):
        pass

    def __str__(self):
        return f"{self.id} {self.from_node} {self.to_node} {self.from_attribute} {self.to_attribute}"

    def __repr__(self):
        return str(self)


class LuaNode(Serializable):
    def __init__(self):
        self.node_type = None
        self.stage = None
        self.id = None
        self.node_attributes = []

    def submit(self, parent):
        node_attribute: NodeAttribute
        for node_attribute in self.node_attributes:
            node_attribute.parent_node = self
            for child in dpg.get_item_children(node_attribute.stage, slot=1):
                dpg.bind_item_font(child, "roboto")
                dpg.move_item(child, parent=self.id)
                # dpg.move_item(node_attribute.id, parent=self.id)
            dpg.delete_item(node_attribute.stage)

        dpg.push_container_stack(parent)
        dpg.unstage(self.stage)
        dpg.pop_container_stack()
        dpg.delete_item(self.stage)

    def generate_code(self):
        return ""

    def serialize(self):
        return {
            "id": self.id,
            "node_pos": dpg.get_item_pos(self.id),
            "node_type": self.node_type,
            "attributes": dict(
                zip([attr.id for attr in self.node_attributes], [attr.serialize() for attr in self.node_attributes]))
        }

    def deserialize(self, obj):
        node_pos = obj["node_pos"]
        dpg.configure_item(self.id, pos=node_pos)
        self.node_type = obj["node_type"]
        i = 0
        for attribute_id, attribute_data in obj["attributes"].items():
            self.node_attributes[i].deserialize(attribute_data)
            i += 1


class LuaVariableNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntVariable

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeVariable()
        ]

        self.attribute_execute_in = self.node_attributes[0]
        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_var = self.node_attributes[2]

        with dpg.stage() as self.stage:
            with dpg.node(label="Variable", pos=dpg.get_mouse_pos(),) as self.id:
                pass

    def has_from_node(self):
        return globals.get_from_node_from_in_node_attribute(self.attribute_execute_in.id) is not None

    def generate_code(self):
        name = dpg.get_value(self.attribute_var.name)
        value = dpg.get_value(self.attribute_var.value)
        if not self.has_from_node():
            return f'{name} = {value}\n'

        execute_out_code = self.attribute_execute_out.generate_code()

        assign_code = f"={value}" if value != "" else ""
        return f"{ind()}local {name} {assign_code}\n{execute_out_code}"


class LuaTable(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntTable

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionOut("Table name", simple=True),
            NodeAttributeMultipleTableEntry(),
            NodeAttributeCustomCallerOut(callback=self.return_table_value)
        ]

        self.attribute_execute_in = self.node_attributes[0]
        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_table_name = self.node_attributes[2]
        self.attribute_table_entries = self.node_attributes[3]
        self.attribute_expression_output = self.node_attributes[4]

        with dpg.stage() as self.stage:
            with dpg.node(label="Table") as self.id:
                pass

    def has_from_node(self):
        ret = globals.get_from_node_from_in_node_attribute(self.attribute_execute_in.id) is not None or globals.get_to_node_from_out_node_attribute(self.attribute_expression_output.id) is not None
        return ret

    def return_table_value(self, params):
        return self.attribute_table_entries.generate_code()

    def generate_code(self):
        name_code = self.attribute_table_name.generate_code()
        local_code = ""
        if self.has_from_node():
            local_code = f"local "
        code_without_flow_out = f"{ind()}{local_code}{name_code} = {self.attribute_table_entries.generate_code()}\n"
        if not self.has_from_node():
            return local_code
        out_code = self.attribute_execute_out.generate_code()
        return f"{code_without_flow_out}{out_code}"

class LuaStartNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntStart

        self.node_attributes = [
            NodeAttributeExecuteOut()
        ]

        self.attribute_execute_out = self.node_attributes[0]

        with dpg.stage() as self.stage:
            with dpg.node(label="Entry point") as self.id:
                pass

    def generate_code(self):
        to_node: LuaNode = globals.get_to_node_from_out_node_attribute(self.attribute_execute_out.id)
        if to_node:
            return to_node.generate_code()
        return ""


class LuaPrintNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntPrint

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionIn("Value")
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_expression = self.node_attributes[2]

        with dpg.stage() as self.stage:
            with dpg.node(label="Print") as self.id:
                pass

    def generate_code(self):
        my_code = self.attribute_expression.generate_code()

        return f"{ind()}print({my_code})\n{self.attribute_execute_out.generate_code()}"


class LuaExpressionNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntExpression

        self.node_attributes = [
            NodeAttributeExpressionOut("Value", simple=True)
        ]

        self.attribute_expression = self.node_attributes[0]

        with dpg.stage() as self.stage:
            with dpg.node(label="Expression") as self.id:
                pass

    def generate_code(self):
        return f"{dpg.get_value(self.attribute_expression.value)}"


class LuaNodeForLoop(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntForLoop

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionOut("Iterator", simple=True),
            NodeAttributeExpressionIn("From"),
            NodeAttributeExpressionIn("To"),
            NodeAttributeExecuteOut("Execute"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_iterator = self.node_attributes[2]
        self.attribute_from = self.node_attributes[3]
        self.attribute_to = self.node_attributes[4]
        self.attribute_code_to_execute = self.node_attributes[5]

        with dpg.stage() as self.stage:
            with dpg.node(label="For Loop") as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_code_to_execute.generate_code()}"
        dec_ind()

        iterator_code = self.attribute_iterator.generate_code()
        from_value = self.attribute_from.generate_code()
        to_value = self.attribute_to.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        return f"{ind()}for {iterator_code} = {from_value}, {to_value} do\n{execute_code}{ind()}end\n{execute_out_code}"


class LuaNodeWhileLoop(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntWhileLoop

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionIn("Condition"),
            NodeAttributeExecuteOut("Execute"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_condition_expression = self.node_attributes[2]
        self.attribute_code_to_execute = self.node_attributes[3]

        with dpg.stage() as self.stage:
            with dpg.node(label="While Loop") as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_code_to_execute.generate_code()}"
        dec_ind()

        condition_code = self.attribute_condition_expression.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        return f"{ind()}while {condition_code} do\n{execute_code}{ind()}end\n{execute_out_code}"


class LuaIfNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntIf

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionIn("Condition"),
            NodeAttributeExecuteOut(text="Condition met"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_expression = self.node_attributes[2]
        self.attribute_execute_if_true = self.node_attributes[3]

        with dpg.stage() as self.stage:
            with dpg.node(label="If") as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_execute_if_true.generate_code()}"
        dec_ind()

        condition_code = self.attribute_expression.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        execute_out_node = globals.get_to_node_from_out_node_attribute(self.attribute_execute_out.id)
        is_last_else_if_or_else = False
        if execute_out_node:
            is_last_else_if_or_else = isinstance(execute_out_node, LuaElseIfNode) or isinstance(execute_out_node,
                                                                                                LuaElseNode)
        return f"{ind()}if {condition_code} then\n{execute_code}{f'{ind()}end{bcksl()}' if not is_last_else_if_or_else else ''}{execute_out_code}"


class LuaElseIfNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntElseIf

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionIn("Condition"),
            NodeAttributeExecuteOut(text="Condition met"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_expression = self.node_attributes[2]
        self.attribute_execute_if_true = self.node_attributes[3]

        with dpg.stage() as self.stage:
            with dpg.node(label="Else If") as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_execute_if_true.generate_code()}"
        dec_ind()

        condition_code = self.attribute_expression.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        execute_out_node = globals.get_to_node_from_out_node_attribute(self.attribute_execute_out.id)
        is_last_else_if_or_else = False
        if execute_out_node:
            is_last_else_if_or_else = isinstance(execute_out_node, LuaElseIfNode) or isinstance(execute_out_node,
                                                                                                LuaElseNode)

        return f"{ind()}else if {condition_code} then\n{execute_code}{f'{ind()}end{bcksl()}' if not is_last_else_if_or_else else ''}{execute_out_code}"


class LuaElseNode(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntElse

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExecuteOut(text="Execute"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_execute_if_true = self.node_attributes[2]

        with dpg.stage() as self.stage:
            with dpg.node(label="Else") as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_execute_if_true.generate_code()}"
        dec_ind()

        execute_out_code = self.attribute_execute_out.generate_code()

        return f"{ind()}else\n{execute_code}{ind()}end\n{execute_out_code}"


class LuaNodeBinaryCombiner(LuaNode):
    def __init__(self, name, symbol):
        super().__init__()
        self.symbol = symbol

        self.node_attributes = [
            NodeAttributeExpressionIn("a"),
            NodeAttributeStaticText(symbol),
            NodeAttributeExpressionIn("b"),
            NodeAttributeCheckbox("Paranthesize"),
            NodeAttributeExpressionOut("Value")
        ]

        self.node_attribute_expression_in_a = self.node_attributes[0]
        self.node_attribute_expression_in_b = self.node_attributes[2]
        self.checkbox_attribute = self.node_attributes[3]

        with dpg.stage() as self.stage:
            with dpg.node(label=name) as self.id:
                pass

    def generate_code(self):
        check = dpg.get_value(self.checkbox_attribute.checkbox)
        return f"{'(' if check else ''}{self.node_attribute_expression_in_a.generate_code()}{self.symbol}{self.node_attribute_expression_in_b.generate_code()}{')' if check else ''}"
        # return self.attribute_expression.generate_code()


class LuaNodeAdd(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Add", " + ")
        self.node_type = lua_ntAdd


class LuaNodeSubtract(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Subtract", " - ")
        self.node_type = lua_ntSubtract


class LuaNodeMultiply(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Multiply", " * ")
        self.node_type = lua_ntMultiply


class LuaNodeDivide(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Divide", " / ")
        self.node_type = lua_ntDivide

class LuaNodeModulus(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Modulus", " % ")
        self.node_type = lua_ntModulus


class LuaNodeGreater(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Greater", " > ")
        self.node_type = lua_ntGreater


class LuaNodeGreaterOrEqual(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Greater or equal", " >= ")
        self.node_type = lua_ntGreaterOrEqual


class LuaNodeLess(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Less", " < ")
        self.node_type = lua_ntLess


class LuaNodeLessOrEqual(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Less or equal", " <= ")
        self.node_type = lua_ntLessOrEqual


class LuaNodeEqual(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Equal", " == ")
        self.node_type = lua_ntEqual


class LuaNodeNotEqual(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Not equal", " ~= ")
        self.node_type = lua_ntNotEqual


class LuaNodeNot(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Not", " not ")
        self.node_type = lua_ntNot


class LuaNodeAnd(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("And", " and ")
        self.node_type = lua_ntAnd


class LuaNodeOr(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Or", " or ")
        self.node_type = lua_ntOr

class LuaNodeConcat(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Concat", " .. ")
        self.node_type = lua_ntConcat


class LuaNodeChainExpression(LuaNodeBinaryCombiner):
    def __init__(self):
        super().__init__("Chain Expression", ", ")
        self.node_type = lua_ntChainExpression


class LuaNodeAssign(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntAssign

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionIn("Variable"),
            NodeAttributeStaticText("="),
            NodeAttributeExpressionIn("Value"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_expression_variable = self.node_attributes[2]
        self.attribute_expression_value = self.node_attributes[4]

        with dpg.stage() as self.stage:
            with dpg.node(label="Assign") as self.id:
                pass

    def generate_code(self):
        variable_code = self.attribute_expression_variable.generate_code()
        value_code = self.attribute_expression_value.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        return f"{ind()}{variable_code} = {value_code}\n{execute_out_code}"


class LuaNodeFunction(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntFunctionDeclaration

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeStaticInputText("Function name"),
            NodeAttributeMultipleExpressionOut("Arguments"),
            NodeAttributeExecuteOut("Execute"),
            NodeAttributeInlineDeclarationOut()
        ]

        self.attribute_execute_in = self.node_attributes[0]
        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_function_name = self.node_attributes[2]
        self.attribute_params = self.node_attributes[3]
        self.attribute_execute_code = self.node_attributes[4]
        self.attribute_inline_decl_out = self.node_attributes[5]

        with dpg.stage() as self.stage:
            with dpg.node(label="Function Declaration") as self.id:
                pass

    def has_from_node(self):
        return globals.get_from_node_from_in_node_attribute(self.attribute_execute_in.id) is not None

    def is_inline(self):
        return globals.get_to_node_from_out_node_attribute(self.attribute_inline_decl_out.id) is not None

    def generate_code(self, inline=False):
        name = self.attribute_function_name.generate_code()

        params_code = "".join([f"{arg.generate_code()}{', ' if i != len(self.attribute_params.arguments) - 1 else ''}" for
                       i, arg in enumerate(self.attribute_params.arguments)])

        execute_code = ""
        # if inline:
        inc_ind()
        execute_code = self.attribute_execute_code.generate_code()
        dec_ind()

        execute_out_code = self.attribute_execute_out.generate_code()

        is_local_function = self.has_from_node()
        local_code = f"{ind()}local " if is_local_function else ""
        end_code = f"\n{execute_out_code}" if not inline else ""
        return f"{local_code}function{f' {name}' if not inline else ''}({params_code})\n{execute_code}{ind()}end{end_code}"


class LuaNodeFunctionCall(LuaNode):

    def __init__(self):
        super().__init__()
        self.node_type = lua_ntFunctionCall

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeStaticInputText("Function name"),
            NodeAttributeExpressionIn("Call On Object"),
            NodeAttributeCheckbox("Call on self"),
            NodeAttributeMultipleExpressionIn("Arguments"),
            NodeAttributeExpressionOut("Out", simple=False)
        ]

        self.attribute_execute_in = self.node_attributes[0]
        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_function_name = self.node_attributes[2]
        self.attribute_call_on_object = self.node_attributes[3]
        self.attribute_call_on_self = self.node_attributes[4]
        self.attribute_params = self.node_attributes[5]

        with dpg.stage() as self.stage:
            with dpg.node(label="Function Call") as self.id:
                pass

    def generate_code(self):
        call_on_object_code = self.attribute_call_on_object.generate_code()

        function_name = self.attribute_function_name.generate_code()
        params_code = "".join(
            [f"{arg.generate_code()}{', ' if i != len(self.attribute_params.arguments) - 1 else ''}" for
             i, arg in enumerate(self.attribute_params.arguments)])

        execute_out_code = self.attribute_execute_out.generate_code()

        end = f"\n{execute_out_code}" if execute_out_code != "" else ""
        call_on_object_code = f"{call_on_object_code}{'.' if not dpg.get_value(self.attribute_call_on_self.checkbox) else ':'}" if call_on_object_code != "" else ""
        # return f"{call_on_object_code}{ind()}{function_name}({params_code}){end}"
        return f"{call_on_object_code}{function_name}({params_code}){end}"


class LuaNodeReturn(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntReturn

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExpressionIn("Value")
        ]

        self.attribute_expression = self.node_attributes[1]

        with dpg.stage() as self.stage:
            with dpg.node(label="Return") as self.id:
                pass

    def generate_code(self):
        my_code = self.attribute_expression.generate_code()

        return f"{ind()}return {my_code}\n"


class LuaNodeIndexTable(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntIndexTable

        self.node_attributes = [
            NodeAttributeExpressionIn("Table"),
            NodeAttributeExpressionIn("Index"),
            NodeAttributeExpressionOut("Output")
        ]

        self.attribute_expression_table = self.node_attributes[0]
        self.attribute_expression_index = self.node_attributes[1]

        with dpg.stage() as self.stage:
            with dpg.node(label="Index Table") as self.id:
                pass

    def generate_code(self):
        table_code = self.attribute_expression_table.generate_code()
        index_code = self.attribute_expression_index.generate_code()
        return f"{table_code}[{index_code}]"


class LuaNodeIndexTableByKey(LuaNode):
    def __init__(self):
        super().__init__()
        self.node_type = lua_ntIndexTableByKey

        self.node_attributes = [
            NodeAttributeExpressionIn("Table"),
            NodeAttributeExpressionIn("Key"),
            NodeAttributeExpressionOut("Output")
        ]

        self.attribute_expression_table = self.node_attributes[0]
        self.attribute_expression_index = self.node_attributes[1]

        with dpg.stage() as self.stage:
            with dpg.node(label="Index Table By Key") as self.id:
                pass

    def generate_code(self):
        table_code = self.attribute_expression_table.generate_code()
        index_code = self.attribute_expression_index.generate_code()
        return f"{table_code}.{index_code}"


class LuaNodeIteratePairs(LuaNode):
    def __init__(self, it1="key", it2="value", iterate_func="ipairs", node_name="Iterate Pairs"):
        super().__init__()
        self.node_type = lua_ntIteratePairs
        self.iterate_func = iterate_func
        self.node_name = node_name

        self.node_attributes = [
            NodeAttributeExecuteIn(),
            NodeAttributeExecuteOut(),
            NodeAttributeExpressionOut(it1, simple=True, value=it1),
            NodeAttributeExpressionOut(it2, simple=True, value=it2),
            NodeAttributeExpressionIn("Table"),
            NodeAttributeExecuteOut("Execute"),
        ]

        self.attribute_execute_out = self.node_attributes[1]
        self.attribute_it1 = self.node_attributes[2]
        self.attribute_it2 = self.node_attributes[3]
        self.attribute_table = self.node_attributes[4]
        self.attribute_code_to_execute = self.node_attributes[5]

        with dpg.stage() as self.stage:
            with dpg.node(label=self.node_name) as self.id:
                pass

    def generate_code(self):
        inc_ind()
        execute_code = f"{self.attribute_code_to_execute.generate_code()}"
        dec_ind()

        it1_code = self.attribute_it1.generate_code()
        it2_code = self.attribute_it2.generate_code()
        table_name = self.attribute_table.generate_code()

        execute_out_code = self.attribute_execute_out.generate_code()

        return f"{ind()}for {it1_code}, {it2_code} in {self.iterate_func}({table_name}) do\n{execute_code}{ind()}end\n{execute_out_code}"


class LuaNodeIterateIPairs(LuaNodeIteratePairs):
    def __init__(self):
        super().__init__("i", "value", "pairs", "Iterate IPairs")
        self.node_type = lua_ntIterateIPairs
