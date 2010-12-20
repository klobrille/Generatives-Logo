package glamoroushochschullogo;

import org.sunflow.image.Color;
import org.sunflow.math.Point3;
import org.sunflow.math.Vector3;

import peasy.PeasyCam;
import processing.core.PApplet;
import processing.pdf.PGraphicsPDF;
import sunflowapiapi.P5SunflowAPIAPI;
import traer.physics.Particle;
import traer.physics.ParticleSystem;
import traer.physics.Vector3D;
import sunflowapiapi.*;

public class GlamorousHochschulLogo extends PApplet {

PeasyCam cam;

P5SunflowAPIAPI sunflow ;
int sceneWidth = 800;
int sceneHeight = 600;
int samples = 32;
int count = 0;
int maxCount = 300;

	int number_of_balls = 20;
	ParticleSystem physics;	
	float bigBoxSize=600;
	float littleBoxSize=1;
	float bounceDamp = 0.5f;
	
	int attractionCount[][] = new int[number_of_balls][number_of_balls];
	boolean checked[][] = new boolean[number_of_balls][number_of_balls];
	
	boolean record;
	PGraphicsPDF pdf;
	
	
	public void setup() {
		size(sceneWidth, sceneHeight,OPENGL);
		//size(800,800,P3D);
		//size(sceneWidth, sceneHeight,"sunflowapiapi.P5SunflowAPIAPI");

//		  sunflow = (P5SunflowAPIAPI) g;
		//hint(ENABLE_OPENGL_4X_SMOOTH);
		
		frameRate(20);
		
	/*	cam = new PeasyCam(this, 300);
		  cam.setMinimumDistance(50);
		  cam.setMaximumDistance(1000); */
		
		physics = new ParticleSystem(0,0,0,0.005f);
		physics.setIntegrator(ParticleSystem.MODIFIED_EULER);

		for(int i=0;i<number_of_balls;i++) {
			physics.makeParticle(1f,
					random(littleBoxSize,200*littleBoxSize),
					random(littleBoxSize,200*littleBoxSize),
					random(littleBoxSize, 200*littleBoxSize));
		}
		
		for(int i=0;i<checked.length;i++){
			for(int j=0; j<checked[i].length; j++){ 
				checked[i][j] = false; 
			} 
		}
		
		for(int i=0; i<number_of_balls; i++){
			for(int j=0; j<number_of_balls; j++) {
				if((i!=j)&&(!checked[i][j])&&(!checked[j][i])){
					checked[i][j] = true;
					checked[j][i] = true;
					physics.makeAttraction(physics.getParticle(j),physics.getParticle(i),random(200),random(50));
				}
			}
		}
	}

	public void draw() {//	beginRaw(PDF,"LogoFlŠche10pSchwarz"+frameCount+".pdf");
		//lights();
		
		
		translate(-100,-100,-700);
		background(250,250,250);
		noStroke();
//		sunflow.setWidth(sceneWidth);
	//	sunflow.setHeight(sceneHeight);
	//	sunflow.setBackground(250,250,250);
		 // sunflow.setSunSkyLight("sunny");
		//sunflow.setDirectionalLight("myDirectionalLight",new Point3(0,1,0), new Vector3(1,1,0),100,new Color(125,125,125));
		  //sunflow.setPointLight(myPointLight, newPoint3(0f,5f,5f), new Color(255,255,255));
		 // sunflow.setCameraPosition(-100, 0, 0);
		//  sunflow.setThinlensCamera("thinLensCamera", 50f, (float)sceneWidth/sceneHeight);
		//  sunflow.setAmbientOcclusionShader();
		//  sunflow.drawPlane("ground", new Point3(0f,-50.4f,0f), new Vector3(0f,1f,0f));
		//  sunflow.setAmbientOcclusionShader();
		 // sunflow.setShinyDiffuseShader(0.5f);

		 // sunflow.setPointLight("myPointLight", new Point3(),	new Color( 255));
		 // sunflow.setPointLight("myPointLight", new Point3(0f,5f,5f), new Color(255f,255f,255f));

		 // sunflow.setDirectionalLight("myDirectionalLight", new Point3(-2f,3f,0f), new Vector3(0f,0f,0f), 3f, new Color(1f,0f,0f));
		//  sunflow.setAmbientOcclusionShader();
		
		physics.tick();
		bounce();
		
		for(int i=0;i<checked.length;i++){
			for(int j=0; j<checked[i].length; j++){ 
				checked[i][j] = false; 
			} 
		}
		
		for(int i=0; i<number_of_balls; i++){
			pushMatrix();
			Particle p = physics.getParticle(i);
			Vector3D v = p.position();
			translate(v.x(),v.y(),v.z());
			noStroke();
			colorMode(RGB);
			//fill(250,250,250);
			noFill();
			box(littleBoxSize);
			popMatrix();
			beginShape(TRIANGLE_STRIP);
			for(int j=0; j<number_of_balls; j++) {
				if((i!=j)&&(!checked[i][j])&&(!checked[j][i])){
					checked[i][j] = true;
					checked[j][i] = true;
					Particle p2 = physics.getParticle(j);
					Vector3D v2 = p2.position();
					if(p.distanceTo(p2) < 100){
						//stroke(216,150,2,20);
						colorMode(HSB,100,100,100,100);
						//stroke(0,205,205,100);
						//stroke(p.distanceTo(p2),50,80,50);
						//strokeWeight(20-(p.distanceTo(p2)/5));
						//fill(100,100,0,100);
						//fill(100,100,0,50);
						//  sunflow.setGlassShader();

						fill(p.distanceTo(p2),50,80,100);
						//strokeWeight(1);
						//line(v.x(),v.y(),v.z(), v2.x(),v2.y(),v2.z());
						vertex(v.x(),v.y(),v.z());
						vertex(v2.x(),v2.y(),v2.z());
						colorMode(RGB);
					}
				}
			}
			endShape(CLOSE);
		}
		//fill(255);
		//text(frameRate, 20, 20);
		//endRaw();
		//record=false;
		
		
		  
		//  sunflow.drawSphereFlake("mySphereFlake", 10, new Vector3(0,1f,0), 1);
		  
		 // sunflow.setPathTracingGIEngine(samples);
		  //sunflow.render("SunflowTestRender_box_testTeil10Punkte"+round(count)+".png");  //+year()+"-"+month()+"-"+day()+"_"+hour()+"-"+minute()+"-"+second()+".png"); 
		  //count++;
		
	}
	
	public void mouseReleased() {
		sunflow.setPathTracingGIEngine(2);
		sunflow.render();
	}
	
private Point3 newPoint3(float f, float g, float h) {
		// TODO Auto-generated method stub
		return null;
	}

public void bounce() {
		
		float collisionPoint = (float) (0.5*(bigBoxSize-littleBoxSize));
		for(int i=0;i<physics.numberOfParticles();i++){
			Particle p = physics.getParticle(i);
			
			
			if(p.position().x()<-collisionPoint) {
				p.position().setX(-collisionPoint);
				p.velocity().setX(-p.velocity().x());
			}
			else
				if(p.position().x()>collisionPoint){
					p.position().setX(collisionPoint);
					p.velocity().setX(-bounceDamp*p.velocity().x());
				}
			
			if(p.position().y()<-collisionPoint) {
				p.position().setY(-collisionPoint);
				p.velocity().setY(-p.velocity().y());
			}
			else
				if(p.position().y()>collisionPoint){
					p.position().setY(collisionPoint);
					p.velocity().setY(-bounceDamp*p.velocity().y());
				}
			
			if(p.position().z()<-collisionPoint) {
				p.position().setZ(-collisionPoint);
				p.velocity().setZ(-p.velocity().z());
			}
			else
				if(p.position().z()>collisionPoint){
					p.position().setZ(collisionPoint);
					p.velocity().setZ(-bounceDamp*p.velocity().z());
				}
			
			
		}
		
		
		
	}
	
	public void keyReleased() {
		if(key=='e'){
			endRecord();
			exit();
		} 
		if(key=='s'){
			record=true;
		}
		if(key=='r'){
			sunflow.setPathTracingGIEngine(32);
			sunflow.render("SunflowTestRender_box_"+round(count)+".png");  }	
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { glamoroushochschullogo.GlamorousHochschulLogo.class.getName() });
	}
}
