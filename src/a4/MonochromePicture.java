package a4;

public class MonochromePicture implements Picture {
	
	private int width;
	private int height; 
	private Pixel value; 
	private Pixel[][] pixel_array;
	
	public MonochromePicture(int width, int height, Pixel value) {
		
			if(width<=0 || height<=0 || value == null) {
				
				throw new IllegalArgumentException("Values should be positive");
			}
		
		this.width = width; 
		this.height = height; 
		this.value = value; 
		this.pixel_array = new Pixel[width][height];
		
				for(int i = 0; i<width; i++) {
					for(int j = 0; j<height; j++) {
						pixel_array[i][j]=value; 
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
		
		if(x<0||y<0||x>=this.getWidth()||y>=this.getWidth()) {
			throw new IllegalArgumentException("Values should be positive");
		}
		
		return pixel_array[x][y];
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		
		Picture mute = new MutablePixelArrayPicture(pixel_array.length,pixel_array[0].length,value);
		
		mute.paint(x, y, p);
		
		
		return mute;	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		
		pixel_array[x][y].blend(p, factor);
		
		return new MutablePixelArrayPicture(pixel_array);	}

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
