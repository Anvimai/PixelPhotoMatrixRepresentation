package a4;

public class MutablePixelArrayPicture implements Picture{
	
	private Pixel[][] pixel_array;
	private int width; 
	private int height; 
	
	// Creates new object using values provided by pixel_array, matching in size. 
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		
		if(pixel_array == null || pixel_array[0].length == 0 || pixel_array[0] == null || pixel_array.length==0) {
			throw new IllegalArgumentException("Must not be null");
		}
		
		for(int i = 0; i < pixel_array.length; i++) {
			if(pixel_array[i]==null) {
				throw new IllegalArgumentException("Must not be null");
			}
			for(int j = 0; j<pixel_array[i].length; j++) {
				if( pixel_array[i][j] == null) {
					throw new IllegalArgumentException("Must not be null");
				}
			}
		}
		
		for(int i =1; i<pixel_array.length; i++) {
			if(pixel_array[i].length != pixel_array[0].length) {
				throw new IllegalArgumentException("All comlumns must be the same height");
			}
		}

		
		this.pixel_array = pixel_array; 
		this.width = pixel_array.length; 
		this.height = pixel_array[0].length; 
	}

	// Creates new object by providing geometry of picture and an initial value for all pixels.
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		
		if(width<=0 || height<=0) {
			throw new IllegalArgumentException("Must not be null");
		}
		
		this.pixel_array = new Pixel[width][height];
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<height; j++) {
				pixel_array[i][j]=initial_value; 
			}
		}
		
	}

	// Creates new object by providing geometry of picture. 
	// Initial value of all pixels should be medium gray (i.e., a grayscale pixel with intensity 0.5)
	public MutablePixelArrayPicture(int width, int height) {
		
		this.pixel_array = new Pixel[width][height];
		
		for(int i = 0; i<width; i++) {
			for(int j = 0; j<height; j++) {
				pixel_array[i][j]= new GrayPixel(0.5); 
			}
		}
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		
		return pixel_array.length;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return pixel_array[0].length;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		
		pixel_array[x][y] = p;
		
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		if(x<0 || y<0 || x >= this.getWidth() || y >= this.getHeight()) {
			
			throw new IllegalArgumentException("X and Y values cannot be negative");
			
		}
		
		pixel_array[x][y].blend(p, factor);
		
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		//Paint Rectangle
		// TODO Auto-generated method stub
		// paint(int ax, int ay, int bx, int by, Pixel p) paints the
		// rectangular region defined by the positions (ax, ay) and
		// (bx, by) with the specified pixel value. 
		
		
		for(int x = 0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(x >= ax && x <=bx && y >= ay && y <= by) {
					//I received help working out the parameters of my if 
					//statement from the JUnit test code
				pixel_array[x][y] = p;}
			}
		}
		
		
		
		return this;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		//int xDistence = Math.abs(ax-bx);
		//int yDistence = Math.abs(ay-by);
		
		//for(int i = ax; i<xDistence; i++) {
			//for(int j=ay; j<yDistence; j++) {
				
				//pixel_array[i][j].blend(p, factor);
				
			//}
			
		//}
		
		int xDistence = Math.abs(ax-bx);
		int yDistence = Math.abs(ay-by);
		
		for(int x = 0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(x >= ax && x <=bx && y >= ay && y <= by) {
					pixel_array[x][y].blend(p, factor);}
			}
		}
		
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		// TODO Auto-generated method stub
		// paint(int cx, int cy, double radius, Pixel p) sets all pixels in the
		// picture that are within radius distance of the coordinate (cx, cy) to the
		// Pixel value p.  Only positive values of radius should be allowed. Any
		// value of cx and cy should be allowed (even if negative or otherwise
		// outside of the boundaries of the frame). 
		
		// Calculate the distance of a particular (x,y) position to (cx,cy) with
		// the expression: Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy))	
		
		if(radius < 0 ) {
			throw new IllegalArgumentException("Radius cannot be negative");
		}
		
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y]= p; }
			}
		}
			
		
		
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y].blend(p, factor); }
			}
		}
		return this;
	}

}
