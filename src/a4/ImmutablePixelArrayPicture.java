package a4;

public class ImmutablePixelArrayPicture implements Picture {

	private Pixel[][] pixel_array;
	private int width; 
	private int height; 
	
	// Creates new object using values provided by pixel_array, matching in size.
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		
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

		
		 
		this.width = pixel_array.length; 
		this.height = pixel_array[0].length; 
		this.pixel_array = pixel_array;
		
		
		
		}
	
	// Creates new object by providing geometry and initial value for all pixels.
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		
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
	
	// Creates new object by providing geometry. Initial value should be medium gray.
	public ImmutablePixelArrayPicture(int width, int height) {
		
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
		//what is wrong here?
				Picture mute = new MutablePixelArrayPicture(pixel_array);
				mute.paint(x, y, p);
		
		return mute;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		if(x<0 || y<0 || x>=height || y>=width) {
			throw new IllegalArgumentException("Must not be null");
		}
		
		pixel_array[x][y].blend(p, factor);
		
		return new ImmutablePixelArrayPicture(pixel_array);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		// TODO Auto-generated method stub
		for(int x = 0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(x >= ax && x <=bx && y >= ay && y <= by) {
					//I received help working out the parameters of my if 
					//statement from the JUnit test code
				pixel_array[x][y] = p;}
			}
		}
		
		return new ImmutablePixelArrayPicture(pixel_array);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// TODO Auto-generated method stub
		int xDistence = Math.abs(ax-bx);
		int yDistence = Math.abs(ay-by);
		
		for(int i = 0; i<xDistence; i++) {
			for(int j=0; j<yDistence; j++) {
				
				pixel_array[i][j].blend(p, factor);
				
			}
			
		}
		
		return new ImmutablePixelArrayPicture(pixel_array);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		// TODO Auto-generated method stub
		if(radius < 0 ) {
			throw new IllegalArgumentException("Radius cannot be negative");
		}
		
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y]= p; }
			}
		}
			
		
		
		return new ImmutablePixelArrayPicture(pixel_array);
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
		return new ImmutablePixelArrayPicture(pixel_array);
	}

}
