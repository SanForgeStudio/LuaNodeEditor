from lupa.lua54 import LuaRuntime
import sys

# def execute():
def execute(code):
    # try:
    #     with open("fileout.txt", "w") as file:
    #         file.write(code)
    #     print("String has been successfully written to", "fielout")
    # except Exception as e:
    #     print("An error occurred:", str(e))

    lua = LuaRuntime()
    lua.execute(code)

if __name__ == '__main__':
    globals()[sys.argv[1]](sys.argv[2])

# execute("test")
# execute()

# execute('''
# local Divider = {
#     Divide = 12,
# }
# local function calculateAndPrintHalf()
#     local halfValue = Divider.Divide / 2
#     return halfValue / 2
# end
# print(calculateAndPrintHalf())
# ''')