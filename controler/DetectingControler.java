package controler;

import java.util.Vector;


import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import model.CommonsFactory;
import model.DetectingFactory;
import model.MatchingFactory;

public class DetectingControler {
	
	public Vector<Mat> matextract= new Vector<Mat>();
	public Mat matorigin=new Mat();
	public Mat matcurrent=new Mat();
	public Vector<Scalar> matfilter=new Vector<Scalar>();
	public Vector<MatOfPoint> boundariescurrent;
	public String color; //blue, red, or all
	
	public DetectingControler(String col,String image){
		matorigin=CommonsFactory.readImage(image);
		color=col;
		if(col.equalsIgnoreCase("blue")){
			matfilter.add(new Scalar(105,105,20));
			matfilter.add(new Scalar(125,255,255));
		}
		if(col.equalsIgnoreCase("red")){
			matfilter.add(new Scalar(0,105,20));
			matfilter.add(new Scalar(10,255,255));
			matfilter.add(new Scalar(170,105,20));
			matfilter.add(new Scalar(179,255,255));
		}
		if(col.equalsIgnoreCase("all")){
			matfilter.add(new Scalar(105,105,20));
			matfilter.add(new Scalar(125,255,255));
			matfilter.add(new Scalar(0,105,20));
			matfilter.add(new Scalar(10,255,255));
			matfilter.add(new Scalar(170,105,20));
			matfilter.add(new Scalar(179,255,255));
		}
	}
	
	public DetectingControler(String col,Mat m){
		matorigin=m;
		color=col;
		if(col.equalsIgnoreCase("blue")){
			matfilter.add(new Scalar(105,105,20));
			matfilter.add(new Scalar(125,255,255));
		}
		if(col.equalsIgnoreCase("red")){
			matfilter.add(new Scalar(0,105,20));
			matfilter.add(new Scalar(10,255,255));
			matfilter.add(new Scalar(170,105,20));
			matfilter.add(new Scalar(179,255,255));
		}
		if(col.equalsIgnoreCase("all")){
			matfilter.add(new Scalar(105,105,20));
			matfilter.add(new Scalar(125,255,255));
			matfilter.add(new Scalar(0,105,20));
			matfilter.add(new Scalar(10,255,255));
			matfilter.add(new Scalar(170,105,20));
			matfilter.add(new Scalar(179,255,255));;
		}
	}
	
	public void rgb2hsv(){
		this.matcurrent=CommonsFactory.bgr2hsv(this.matorigin);
	}
	
	public void filter() throws Exception{
		this.matcurrent=DetectingFactory.filterImageByColor(this.matcurrent, this.matfilter.size()/2, this.matfilter);
	}
	
	public void Boundaries(){
		this.boundariescurrent=DetectingFactory.findBoundaries(this.matcurrent);
	}
	
	public void eliminateBoundaries(){
		this.boundariescurrent=DetectingFactory.eliminateBoundaries(this.boundariescurrent, this.matorigin);
	}
	
	public void extract(){
		this.matextract=DetectingFactory.extractPanel(this.boundariescurrent, this.matorigin);
	}
	
	public Mat extractForGraphics(){
		return DetectingFactory.extractPanelForGraphics(this.boundariescurrent, this.matorigin);
	}
	
	public void extract22222(){
		this.matextract=DetectingFactory.extractPanel22222(this.boundariescurrent, this.matorigin);
	}

	public Vector<Mat> getMatextract() {
		
		return matextract;
	}

	public void setMatextract(Vector<Mat> matextract) {
		this.matextract = matextract;
	}

	public Mat getMatorigin() {
		return matorigin;
	}

	public void setMatorigin(Mat matorigin) {
		this.matorigin = matorigin;
	}

	public Mat getMatcurrent() {
		return matcurrent;
	}

	public void setMatcurrent(Mat matcurrent) {
		this.matcurrent = matcurrent;
	}
	
	

}