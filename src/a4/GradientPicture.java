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
		if(width==0||height==0||upper_left==null||upper_right==null
			||lower_left==null||lower_right==null) {
			
			throw new IllegalArgumentException("Values cannot be null");
			
		}
		
		
		
		this.width = width; 
		this.height = height; 
		this.upper_left = upper_left; 
		this.upper_right = upper_right; 
		this.lower_left = lower_left; 
		this.lower_right = lower_right; 
		this.pixel_array = new Pixel[width][height];
		this.pixel_array[0][0] = upper_left;
		this.pixel_array[width-1][height-1] = lower_right; 
		this.pixel_array[0][height-1] = lower_left; 
		this.pixel_array[width-1][0] = upper_right; 
		
		for(int y = 0; y<1; y++) {
			for(int x =1; x<width-1; x++) {
				pixel_array[x][y] = upper_left.blend(upper_right, (1.0/((this.getWidth()-1)))*x);
			}
		}
		for(int y = height-1; y<height; y++) {
			for(int x =1; x<width-1; x++) {
				pixel_array[x][y] = lower_left.blend(lower_right, (1.0/((this.getWidth()-1)))*x);
			}
		}
		for(int x = 0; x<width; x++) {
			for(int y =1; y<height-1; y++) { 
				double inverse = (y/4);
				pixel_array[x][y] = pixel_array[x][0].blend(pixel_array[x][height-1], ((1.0/(height-1))*y));
				
			}
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
	
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		
		Pixel[][] mute_array = new Pixel[this.getWidth()][this.getHeight()];
		for(int x1=0; x1<this.getWidth();x1++) {
			for(int y1=0;y1<this.getHeight();y1++) {
				mute_array[x1][y1]=pixel_array[x1][y1];
			}
		}
		Picture mute = new MutablePixelArrayPicture(mute_array);
		
		mute.paint(x, y, p);
		
		
		return mute;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		if(x<0||x>=this.getWidth()||y>=this.getHeight()||y<0) {
			
			throw new IllegalArgumentException("Values must not be null");
			
		}
		
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
