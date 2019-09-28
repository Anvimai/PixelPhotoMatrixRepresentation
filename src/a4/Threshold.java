package a4;

public class Threshold implements PixelTransformation {
	
	private double threshold; 
	
	public Threshold (double threshold) {
		
		this.threshold = threshold; 
		
	}

	
	public Pixel transform(Pixel p) {
		// TODO Auto-generated method stub
		
		if(p.getIntensity()>threshold) {
			return p.lighten(1.0);
		}
		return p.darken(1.0);
	}

}
