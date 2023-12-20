# 
# Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
# Licensed under the GNU General Public 3.0 License
#


import dearpygui.dearpygui as dpg
from LuaNodes import *

# dpg.add_theme_color(dpg.mvNodeCol_NodeBackground, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_NodeBackgroundHovered, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_NodeBackgroundSelected, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_NodeOutline, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_TitleBar, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_TitleBarHovered, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
# dpg.add_theme_color(dpg.mvNodeCol_TitleBarSelected, (255, 0, 0, 0), category=dpg.mvThemeCat_Nodes)
themes = {

}

def create_theme_from_color(color):
    with dpg.theme() as ret:
        with dpg.theme_component(dpg.mvAll):
            dpg.add_theme_color(dpg.mvNodeCol_TitleBar, color, category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_TitleBarHovered, (color[0] + 10, color[1] + 10, color[2] + 10), category=dpg.mvThemeCat_Nodes)
            dpg.add_theme_color(dpg.mvNodeCol_TitleBarSelected, (color[0] + 20, color[1] + 20, color[2] + 20), category=dpg.mvThemeCat_Nodes)
    return ret

def init_themes():
    pink_theme = create_theme_from_color((105, 0, 105))
    green_theme = create_theme_from_color((20, 150, 10))
    blue_theme = create_theme_from_color((10, 20, 200))
    red_theme = create_theme_from_color((190, 10, 30))
    purple_theme = create_theme_from_color((190, 10, 180))
    yellow_theme = create_theme_from_color((140, 180, 10))
    brown_theme = create_theme_from_color((115, 42, 42))
    white_theme = create_theme_from_color((170, 170, 170))

    global themes
    themes[(lua_ntStart, )] = pink_theme
    themes[(lua_ntGreater, lua_ntGreaterOrEqual, lua_ntLess, lua_ntLessOrEqual, lua_ntEqual, lua_ntNotEqual)] = green_theme
    themes[(lua_ntAdd, lua_ntSubtract, lua_ntMultiply, lua_ntDivide,lua_ntModulus)] = blue_theme
    themes[(lua_ntVariable, lua_ntTable, lua_ntExpression)] = red_theme
    themes[(lua_ntFunctionCall, lua_ntFunctionDeclaration)] = purple_theme
    themes[(lua_ntWhileLoop, lua_ntForLoop, lua_ntIteratePairs, lua_ntIterateIPairs, lua_ntIf, lua_ntElseIf, lua_ntElse)] = yellow_theme
    themes[(lua_ntConcat, lua_ntChainExpression)] = brown_theme
    themes[(lua_ntAssign, lua_ntIndexTableByKey, lua_ntIndexTable)] = white_theme

def apply_theme(node: LuaNode):
    for key, theme in themes.items():
        if node.node_type in key:
            dpg.bind_item_theme(node.id, theme)
