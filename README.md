# PAINT-JAVAFX

Authors: 
Mateo Andrioli, Santino Giambelluca, Franco Pedretti, Ignacio Andreotti

Language: Java

Frameworks: JavaFX

-------------------------------------------
DESCRIPTION
-------------------------------------------

This is a JavaFX-based drawing application

The backend handles figures (e.g., rectangles, squares) as abstract, logic-only entities with operations like:
- Move
- Resize
- Color
- Divide (width, height)
- Mirror
- Apply visual effects

The frontend renders those figures using JavaFX and provides an interactive menu bar, mouse handling, and a canvas pane.

-------------------------------------------
FEATURES
-------------------------------------------

✔ Draw and manipulate figures with the mouse  
✔ Apply effects: lighten, darken, mirror(horizontal/vertical)  
✔ Divide figures into parts (width-wise, height-wise)   
✔ Menu system  
✔ Clean MVC-like separation (backend logic vs frontend rendering)

-------------------------------------------
HOW TO RUN
-------------------------------------------

1. Compile the project using any Java IDE or command line


2. Run the main class: AppLauncher

*Ensure JavaFX SDK is correctly set up in your environment.*

Refer to this link for JavaFX installation: https://gluonhq.com/products/javafx/

-------------------------------------------
USAGE
-------------------------------------------

🖱 Left Click & Drag: Draw figure   
🖱 Drag Selected: Move  
🧩 Use Menu Bar to:
- Exit the app
- Show About info

-------------------------------------------
NOTES
-------------------------------------------

- Avoid directly mutating JavaFX `Node` from model layer
- Backend logic is UI-agnostic: all drawing is done in `CanvasFigure`
- Designed for academic use — not yet optimized for large-scale figures or persistent storage
