# Assignment 4

## Pixels and Pictures

Digital pictures are usually represented as a two-dimensional grid of "picture elements" or "pixels". Each pixel represents the color of a particular spot on the picture and the resolution of a picture can be expressed in terms of how wide and tall the picture is in pixel units.  

### Pixels

A color (to a computer) is a specific formula of three components: red, green, and blue. We will be using values in the range of 0.0 up to 1.0 for each of these components. A 0.0 value represents no amount of that component and 1.0 is the maximum amount of that component. When the red, green, and blue components all equal each other, you get a color on the "grayscale" spectrum from black (all 0.0’s) to white (all 1.0’s). The chromatic colors are formed when the values for red, green, and blue differ from each other.

### Pictures

The Picture interface defines an abstraction for representing a 2-dimensional frame of pixels. This abstraction will provide a number of methods to query and possibly set various properties including individual pixel values. Individual pixel values are addressed by their position (i.e., x and y coordinates) within the frame. The x-coordinate represents the column of the pixel and the y-coordinate represents the row of the pixel. The top of the picture is the first (i.e., 0th row) and the bottom is the last row. This means the upper left corner of the picture has the (x,y) coordinate (0,0) and the lower right picture has the coordinate (w-1, h-1) where w and h are the width and height of the picture.
 
## Novice

Create two implementations of Picture as follows.

 * MutablePixelArrayPicture
   * MutablePixelArrayPicture should implement Picture by encapsulating a 2D array of pixels that are mutable (i.e., allowed to change). It should have the following constructor forms:   

   The first dimension of pixel_array is the width and the second is the height. In other words, pixel_array.length will be the width of the picture and pixel_array[0].length will be the height of the picture. The pixel at coordinate (x,y) is located at pixel_array[x][y]. 
   
 * MonochromePicture
   * MonochromePicture should implement a Picture that has the same value for Pixel in every position. This value is provided to the constructor along with the width and height of the picture. The constructor should have the following form:
   
 ## Adept
 Create four more implementations of Picture as follows.

 * ImmutablePixelArrayPicture
   * ImmutablePixelArrayPicture should implement Picture by encapsulating a 2D array of pixels that are immutable (i.e., NOT allowed to change).
 
 * GradientPicture
   * GradientPicture should implement a Picture that is a smooth blend of pixel values specified for its four corners. In other words, any pixel in the middle of the picture is a proportional blend of the pixel values associated with its corners. The blend is inversely proportional to the distance of the pixel to those corners. For example, pixel values along the top row of the picture start off as the specified upper_left value and then become more and more like the upper_right value as you go across.

 * HorizontalStackPicture and VerticalStackPicture
   * These implementations will encapsulate references to two Picture objects and will represent them as if they were a larger Picture object that resulted from "stacking" them either horizontally or vertically. The constructors for these new classes should have the following form:

## Jedi

Create an interface called PixelTransformation.

Now create two implementations of PixelTransformation called: Threshold and GammaCorrect

The transform method of a Threshold object should produce either a white pixel or a black pixel depending on the intensity (i.e., brightness) of the pixel p passed to it. If p’s brightness is strictly above the threshold value specified in the constructor, then a white pixel is returned. Otherwise, a black pixel is returned.

The transform method of a GammaCorrect object should produce a "gamma corrected" version of pixel p passed to it. The components (i.e., red, green, and blue) of the gamma corrected pixel are the result of raising them to the (1.0/gamma) power. In other words, if "old" is the original component value in the range from 0.0 to 1.0, then "new" can be calculated as:

Create a new implementation of Picture called TransformedPicture.

A TransformedPicture should encapsulate the provided source Picture object and PixelTransformation object. A TransformedPicture object is expected to transform the pixel values of the source frame on demand when getPixel is called using the pixel transformation object provided to the constructor.
