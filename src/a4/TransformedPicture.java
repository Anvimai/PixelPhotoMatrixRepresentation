package a4;

public class TransformedPicture implements Picture{
	
	private Picture source; 
	private PixelTransformation xform; 
	private Picture pic; 
	private PixelTransformation form; 
	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		
		this.source = source; 
		this.xform = xform;
		
		 this.pic = source; 
		 this.form = xform; 
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return source.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return source.getHeight();
	}

	@Override
	public Pixel getPixel(int x, int y) {
		// TODO Auto-generated method stub
		
		
		
		return form.transform(pic.getPixel(x,y));
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		return source.paint(x, y, p);
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return source.paint(x, y, p, factor);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		// TODO Auto-generated method stub
		return source.paint(ax, ay, bx, by, p);
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return source.paint(ax, ay, bx, by, p, factor);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		// TODO Auto-generated method stub
		return source.paint(cx, cy, radius, p);
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return source.paint(cx, cy, radius, p, factor);
	}

}
