package a4;

public class GammaCorrect implements PixelTransformation{

	private double gamma; 
	
	public GammaCorrect (double gamma) {
		
		
		
		this.gamma = gamma; 
		
	}
	
	public Pixel transform(Pixel p) {
		// TODO Auto-generated method stub
		
		
		
		Pixel pix = new ColorPixel(Math.pow(p.getRed(), 1.0/gamma),
												Math.pow(p.getGreen(), 1.0/gamma),
													Math.pow(p.getBlue(), 1.0/gamma));
	
		
		
		return pix;
		

	}

}
