package a4;

public class GradientPicture implements Picture{
	
	private Pixel[][] pixel_array;
	private int width; 
	private int height; 
	private Pixel upper_left; 
	private Pixel lower_left;
	private Pixel upper_right;
	private Pixel lower_right; 
	
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right,
									Pixel lower_left, Pixel lower_right) {
		this.width = width; 
		this.height = height; 
		this.upper_left = upper_left; 
		this.upper_right = upper_right; 
		this.lower_left = lower_left; 
		this.lower_right = lower_right; 
		this.pixel_array = new Pixel[width][height];
		pixel_array[0][0] = upper_left;
		pixel_array[width-1][height-1] = lower_right; 
		pixel_array[0][height-1] = lower_left; 
		pixel_array[width-1][0] = upper_right; 
		
	
		for(int i = 1; i<(width-1)/2; i++) {
			
			//pixel_array[i][0].blend(pixel_array[0][0], 1.0/i);
			
		}

		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub

		if(x<0||y<0||x>=this.getWidth()||y>=this.getWidth()) {
			throw new IllegalArgumentException("Values should be positive");
		}
		
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		Picture mute = new MutablePixelArrayPicture(pixel_array);
		
		mute.paint(x, y, p);
		
		
		return mute;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		pixel_array[x][y].blend(p, factor);
		
		return new MutablePixelArrayPicture(pixel_array);
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
		
		return new MutablePixelArrayPicture(pixel_array);	}

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
		
		return new MutablePixelArrayPicture(pixel_array);	}

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
			
		
		
		return new MutablePixelArrayPicture(pixel_array);	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y].blend(p, factor); }
			}
		}
		return new MutablePixelArrayPicture(pixel_array);	}

}
