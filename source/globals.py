from abc import ABC, abstractmethod
import dearpygui.dearpygui as dpg

nodes = []
links = []

indent_level = 0


class Serializable(ABC):

    @abstractmethod
    def serialize(self):
        pass

    @abstractmethod
    def deserialize(self, obj):
        pass

def ind():
    global indent_level
    return "    " * indent_level


def bcksl():
    return "\n"


def inc_ind():
    global indent_level
    indent_level += 1
    return ""


def dec_ind():
    global indent_level
    indent_level -= 1
    return ""


def get_node_from_id(id):
    for node in nodes:
        if node.id == id:
            return node
    return None


def get_link_from_id(id):
    for link in links:
        if link.id == id:
            return link
    return None


def get_link_from_in_node_attribute(in_id):
    for link in links:
        if link.to_attribute == in_id:
            return link
    return None


def get_link_from_out_node_attribute(out_id):
    for link in links:
        if link.from_attribute == out_id:
            return link
    return None


def get_nodes_from_link(link):
    # for l in links:
    #     if l == link:
    #         return [l.from_node, l.to_node]
    # return None
    return [get_node_from_id(link.from_node), get_node_from_id(link.to_node)]


def get_from_node_from_in_node_attribute(in_id):
    link = get_link_from_in_node_attribute(in_id)
    if link:
        return get_nodes_from_link(link)[0]


def get_to_node_from_out_node_attribute(out_id):
    link = get_link_from_out_node_attribute(out_id)
    if link:
        return get_nodes_from_link(link)[1]


def get_out_node_attribute_from_in_node_attribute(in_id):
    from LuaNodes import LuaNode, Link
    from LuaNodeAttributes import NodeAttribute

    link: Link = get_link_from_in_node_attribute(in_id)
    if link:
        node: LuaNode = get_nodes_from_link(link)[0]
        n_a: NodeAttribute
        for n_a in node.node_attributes:
            if n_a.id == link.from_attribute:
                return n_a
    # if link:
    #     node: LuaNode = get_nodes_from_link(link)[0]
    #     n_a: NodeAttribute
    #     # just get the node attributes as the children of the node?
    #     for n_a_id in dpg.get_item_children(node.id, slot=1):
    #         if n_a_id == link.from_attribute:
    #             return n_a


def get_in_node_attribute_from_out_node_attribute(out_id):
    from LuaNodes import LuaNode, Link
    from LuaNodeAttributes import NodeAttribute

    link: Link = get_link_from_out_node_attribute(out_id)
    if link:
        node: LuaNode = get_nodes_from_link(link)[1]
        n_a: NodeAttribute
        for n_a in node.node_attributes:
            if n_a.id == link.to_attribute:
                return n_a
