# 
# Copyright © 2023, SanForge Studio & Lua Node Editor, All Rights Reserved.
# Licensed under the GNU General Public 3.0 License
#

import tkinter as tk
from PIL import Image, ImageTk

class SplashScreen:
    def __init__(self, image_path, width=600, height=200, duration=3000):
        self.image_path = image_path
        self.width = width
        self.height = height
        self.duration = duration
        self.root = tk.Tk()

    def show(self):
        self.root.overrideredirect(True)

        screen_width = self.root.winfo_screenwidth()
        screen_height = self.root.winfo_screenheight()

        center_x = int((screen_width - self.width) / 2)
        center_y = int((screen_height - self.height) / 2)

        self.root.geometry(f"{self.width}x{self.height}+{center_x}+{center_y}")

        self.display_image()

        self.root.after(self.duration, self.root.destroy)

        self.root.mainloop()

    def display_image(self):
        original_image = Image.open(self.image_path)

        resized_image = original_image.resize((self.width, self.height))

        photo_image = ImageTk.PhotoImage(resized_image)

        label = tk.Label(self.root, image=photo_image)
        label.image = photo_image
        label.pack()
