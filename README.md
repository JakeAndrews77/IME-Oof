# Image Processing with provider code
## Edits summary
Mosaic of image implementation: Y<br>
Script command to mosaic image: N<br>
Mosaic of image from GUI: N<br>
- We implemented script support to test the mosaic methods analogously to the other script command tests, but because the text version of the program fails to run functions other than mosaicking, the encapsulating test could not be run to verify the command's operation.
- The GUI was additionally non-functional, throwing an exception upon invocation with the proper arguments to the program's main function.
## Our work
In order to implement mosaic processing in our provider's codebase, we took the following steps:
- We directly implemented the image mosaic logic. This involved adding a mosaic method to the AbstractImage class--which contains the other image manipulation functionality--that selected a number of randomly-located 2D pixel array positions equal to the desired amount of seeds and then re-colored (in helper method getSeedColors) each pixel based on the seed position they were closest to; this was after modifying the IMEImage interface to contain the mosaic method.
- We called the AbstractImage class's mosaic method from an added corresponding mosaic method in the AbstractModel class analogously to how the other AbstractImage functions are invoked; this was after modifying the IMEModel interface to contain the mosaic method.
- We added support in IMEControllerImp for the multi-argument mosaic command in the same clause as the brightness command's text/script argument-handling.
- For testing, we added a mosaic function to the MockModel class that appends an output to the view appendable. To test text/script operation, we added a corresponding sequence of commands for mosaicking to the ControllerTest class. It is worth noting, however, that the controller's test did not orginally run.