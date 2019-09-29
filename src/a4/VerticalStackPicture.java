package a4;

public class VerticalStackPicture implements Picture{
	
	private Picture top;
	private Picture bottom; 
	private Pixel[][] pixel_array;
	
	public VerticalStackPicture(Picture top, Picture bottom) {
		
		if(top==null||bottom==null) {
			throw new IllegalArgumentException("Left and Right pictures must not be null");
			
		}
		if(top.getWidth()!=bottom.getWidth()) {
			throw new IllegalArgumentException("Widths must match");
			
		}
		
		this.top = top;
		this.bottom = bottom; 
		
		
		
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return (top.getWidth());
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return (top.getHeight() + bottom.getHeight());
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		if(x<bottom.getHeight()) {
			
			
			return bottom.getPixel(x, y); 
		}
		
		if(x>=bottom.getHeight()) {
			

			return top.getPixel(x, y-bottom.getHeight());
		}
		
		return null;
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		
		if(y<top.getHeight()) {
			
			top.paint(x, y, p);
			return this; 
		}
		
		if(y>top.getHeight()) {
			
			bottom.paint(x, y-top.getHeight(), p);
			return this; 
		}
		
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		if(x<0 || y<0 || x >= this.getWidth() || y >= this.getHeight() || p==null 
				|| factor < 0 || factor > 1) {
			
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
		
		
		for(int x = 0; x<(top.getWidth()); x++) {
			for(int y=0; y<top.getHeight()+bottom.getHeight(); y++) {
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
		
		for(int x = 0; x<(top.getWidth()); x++) {
			for(int y=0; y<top.getHeight()+bottom.getHeight(); y++) {
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
		
		for(int x = 0; x<(top.getWidth()); x++) {
			for(int y = 0; y<top.getHeight()+bottom.getHeight(); y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y]= p; }
			}
		}
			
		
		
		return this;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		
		for(int x = 0; x<(top.getWidth()); x++) {
			for(int y = 0; y<top.getHeight()+bottom.getHeight(); y++) {
				if(Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy)) <= radius){
				pixel_array[x][y].blend(p, factor); }
			}
		}
		return this;
	}

}
