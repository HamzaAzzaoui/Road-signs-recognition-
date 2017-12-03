package Graphics;

import model.DetectingFactory;
import controler.DetectingControler;
import controler.MatchingControler;
import controler.MatchingControler2;
import model.CommonsFactory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Image;

import javax.swing.filechooser.FileNameExtensionFilter;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;


import javax.swing.JToggleButton;
import javax.swing.JProgressBar;




public class FenetrePrincipal extends JFrame implements Runnable {
	
	public static Mat m;
	private JPanel contentPane;
	private Thread T;
	private static JLabel afficheVideo;
	private static int chargerImage=0;
	private static int arretVideo=1;
	private static JPanel affiche;
	private static Mat imageVideo = null;
	private static int mode=0;
	private static String adresseVideo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipal frame = new FenetrePrincipal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipal() {
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 290);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		affiche = new JPanel();
		affiche.setBounds(10, 475, 985, 175);
		affiche.setBackground(Color.WHITE);
		
		final JLabel ensemLogo = new JLabel("");
		ensemLogo.setBounds(35, 200, 190, 70);
		ensemLogo.setIcon(new ImageIcon("ensem.png"));
		contentPane.add(ensemLogo);
		
		final JLabel renaultLogo = new JLabel("");
		renaultLogo.setIcon(new ImageIcon("renault.jpg"));
		renaultLogo.setBounds(225, 197, 190, 70);
		contentPane.add(renaultLogo);
		
		final JButton btnModeAutomatique = new JButton("Mode automatique");
		btnModeAutomatique.setBackground(Color.BLACK);
		btnModeAutomatique.setForeground(Color.WHITE);
		btnModeAutomatique.setBounds(175, 25, 220, 35);
		contentPane.add(btnModeAutomatique);
		
		final JButton btnModePas = new JButton("Mode pas à pas");
		btnModePas.setForeground(Color.WHITE);
		btnModePas.setBackground(Color.BLACK);
		btnModePas.setBounds(175, 80, 220, 35);
		contentPane.add(btnModePas);
		
		final JButton btnModeVideo = new JButton("Mode video");
		btnModeVideo .setForeground(Color.WHITE);
		btnModeVideo .setBackground(Color.BLACK);
		btnModeVideo .setBounds(175, 135, 220, 35);
		contentPane.add(btnModeVideo );
		
		final JButton btnAide = new JButton("");
		btnAide.setBounds(485, 25, 35, 35);
		btnAide.setIcon(new ImageIcon("interrogation.png"));
		contentPane.add(btnAide);
		
		final JButton btnHome = new JButton("");
		btnHome.setBounds(960, 10, 35, 35);
		btnHome.setBackground(Color.BLACK);
		btnHome.setIcon(new ImageIcon("HOME.png"));
		
		final JLabel twizyLogo = new JLabel("");
		twizyLogo.setIcon(new ImageIcon("twizy.jpg"));
		twizyLogo.setBounds(400, 205, 190, 70);
		contentPane.add(twizyLogo);
		
		final JButton btnChargerImage = new JButton("Charger Image");
		btnChargerImage.setBounds(110, 10, 160, 35);
		btnChargerImage.setBackground(Color.BLACK);
		btnChargerImage.setForeground(Color.WHITE);
		
		final JButton btnChargerVideo = new JButton("Charger Video");
		btnChargerVideo.setBounds(400, 10, 160, 35);
		btnChargerVideo.setBackground(Color.BLACK);
		btnChargerVideo.setForeground(Color.WHITE);
		
		final JButton traiterImage = new JButton("Traiter Image");
		traiterImage.setBounds(420, 200, 160, 35);
		traiterImage.setBackground(Color.BLACK);
		traiterImage.setForeground(Color.WHITE);
		
		final JButton bgr2gray = new JButton("bgr2gray");
		bgr2gray.setBounds(420, 100, 160, 35);
		bgr2gray.setBackground(Color.BLACK);
		bgr2gray.setForeground(Color.WHITE);
		
		final JButton bgr2hsv = new JButton("bgr2hsv");
		bgr2hsv.setBounds(420, 150, 160, 35);
		bgr2hsv.setBackground(Color.BLACK);
		bgr2hsv.setForeground(Color.WHITE);
		
		final JButton seuillage = new JButton("Seuillage");
		seuillage.setBounds(420, 200, 160, 35);
		seuillage.setBackground(Color.BLACK);
		seuillage.setForeground(Color.WHITE);
		
		final JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(4, 50, 2, 401);
		
		final JSeparator separator1 = new JSeparator();
		separator1.setForeground(Color.BLACK);
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(405, 50, 2, 401);
		
		final JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.BLACK);
		separator2.setOrientation(SwingConstants.HORIZONTAL);
		separator2.setBounds(4, 49, 401, 2);
		
		final JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.BLACK);
		separator3.setOrientation(SwingConstants.HORIZONTAL);
		separator3.setBounds(4, 450, 401, 2);
		
		final JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.BLACK);
		separator4.setOrientation(SwingConstants.VERTICAL);
		separator4.setBounds(594, 50, 2, 401);
		
		final JSeparator separator5 = new JSeparator();
		separator5.setForeground(Color.BLACK);
		separator5.setOrientation(SwingConstants.VERTICAL);
		separator5.setBounds(995, 50, 2, 400);
		
		final JSeparator separator6 = new JSeparator();
		separator6.setForeground(Color.BLACK);
		separator6.setOrientation(SwingConstants.HORIZONTAL);
		separator6.setBounds(594, 49, 401, 2);
		
		final JSeparator separator7 = new JSeparator();
		separator7.setForeground(Color.BLACK);
		separator7.setOrientation(SwingConstants.HORIZONTAL);
		separator7.setBounds(594, 450, 401, 2);
		
		final JSeparator separator8 = new JSeparator();
		separator8.setForeground(Color.BLACK);
		separator8.setOrientation(SwingConstants.VERTICAL);
		separator8.setBounds(5, 470, 2, 180);
		
		final JSeparator separator9 = new JSeparator();
		separator9.setForeground(Color.BLACK);
		separator9.setOrientation(SwingConstants.VERTICAL);
		separator9.setBounds(995, 470, 2, 180);
		
		final JSeparator separator10 = new JSeparator();
		separator10.setForeground(Color.BLACK);
		separator10.setOrientation(SwingConstants.HORIZONTAL);
		separator10.setBounds(5, 470, 991, 2);
		
		final JSeparator separator11 = new JSeparator();
		separator11.setForeground(Color.BLACK);
		separator11.setOrientation(SwingConstants.HORIZONTAL);
		separator11.setBounds(5, 650, 990, 2);
		
		
		final JFileChooser file = new JFileChooser();
		FileNameExtensionFilter FiltreText = new FileNameExtensionFilter("Fichier Image","png", "jpg", "jpeg");
		file.setFileFilter(FiltreText);
		
		final JLabel afficheimage = new JLabel("");
		afficheimage.setBounds(5, 50, 400, 400);
		
		
		final JLabel afficheimage2 = new JLabel("");
		afficheimage2.setBounds(595, 50, 400, 400);
		
		final JLabel textChoix = new JLabel(" Choisir un mode :");
		textChoix.setBounds(30, 40, 170, 170);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(30, 140, 170, 24);
		comboBox.addItem(" ");
		comboBox.addItem("Webcam");
		comboBox.addItem("Charger video");
		
		final JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.BLACK);
		btnOk.setForeground(Color.WHITE);
		btnOk.setBounds(65, 180, 100, 35);
		
		final JButton btnArret = new JButton("Arrêt");
		btnArret.setBackground(Color.BLACK);
		btnArret.setForeground(Color.WHITE);
		btnArret.setBounds(770, 180, 100, 35);
		
		afficheVideo = new JLabel("");
		afficheVideo.setBounds(235, 50, 501, 400);
		
		
		btnChargerImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				afficheimage2.setIcon(null);
				int returnVal = file.showDialog(new JFrame(),"Ouvrir");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					String adresse=file.getSelectedFile( ).getAbsolutePath( );	
					
					m = CommonsFactory.readImage(adresse);
					MatOfByte mat_of_byte = new MatOfByte();
					Highgui.imencode( ".png", m, mat_of_byte );
					byte[] byte_array = mat_of_byte.toArray();
					BufferedImage buf_img = null;
					
					try {
						InputStream in = new ByteArrayInputStream( byte_array );
						buf_img = ImageIO.read( in );
						ImageIcon i = new ImageIcon(buf_img);
						Image image = i.getImage();
						Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
						i = new ImageIcon(newimg);
						afficheimage.setIcon(i);
						chargerImage=1;
						affiche.removeAll();
						affiche.repaint();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		btnChargerVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				afficheimage2.setIcon(null);
				int returnVal = file.showDialog(new JFrame(),"Ouvrir");
				if (returnVal == JFileChooser.APPROVE_OPTION){
					adresseVideo=file.getSelectedFile( ).getAbsolutePath( );
					contentPane.repaint();
			        contentPane.add(afficheVideo);
			        start(); 
				}
				
			}
		});
		
		traiterImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arretVideo=1;
				if(chargerImage==0){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Charger une image", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				try {
					DetectingControler d = new DetectingControler("all",m);
					d.rgb2hsv();
					d.filter();
					d.Boundaries();
					d.eliminateBoundaries();
					Mat m2 = d.extractForGraphics();
					
					MatOfByte mat_of_byte = new MatOfByte();
					Highgui.imencode( ".png", m2, mat_of_byte );
					byte[] byte_array = mat_of_byte.toArray();
					BufferedImage buf_img = null;
					InputStream in = new ByteArrayInputStream( byte_array );
					buf_img = ImageIO.read( in );
					ImageIcon i = new ImageIcon(buf_img);
					Image image = i.getImage();
					Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					i = new ImageIcon(newimg);
					afficheimage2.setIcon(i);
					
					
					d.eliminateBoundaries();
					d.extract();
					
					Vector<Mat> toMatchVector = new Vector<Mat>();
					for( Mat image1 : d.matextract ) {
						toMatchVector.add( image1 );
					}
					MatchingControler2 test = new MatchingControler2( toMatchVector );
					test.matchAll();
					
					for(int i1=0;i1<d.matextract.size();i1++){
						Mat m3 = d.matextract.get(i1);
						MatOfByte mat_of_byte2 = new MatOfByte();
						Highgui.imencode( ".png", m3, mat_of_byte2 );
						byte[] byte_array2 = mat_of_byte2.toArray();
						BufferedImage buf_img2 = null;
						InputStream in2 = new ByteArrayInputStream( byte_array2 );
						buf_img2 = ImageIO.read( in2 );
						ImageIcon i2 = new ImageIcon(buf_img2);
						Image image2 = i2.getImage();
						Image newimg2 = image2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
						i2 = new ImageIcon(newimg2);
						
						final JLabel afficheimage10 = new JLabel("");
						afficheimage10.setBounds(5+i1, 5, 100, 100);
						afficheimage10.setIcon(i2);
						affiche.add(afficheimage10);
						setContentPane(contentPane);
						
						final JLabel afficheimage4 = new JLabel(test.matchingDecision.get(i1));
						afficheimage4.setBounds(5+2*i1+35, 105, 100, 100);
						afficheimage4.setFont(new Font("Times New Roman", Font.BOLD, 24));
						affiche.add(afficheimage4);
						setContentPane(contentPane);
						affiche.repaint();
						
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		
		
		btnAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenetreAide frame = new FenetreAide();
				frame.setLocation(1200, 200);
				frame.setVisible(true);
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arretVideo==0){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Arreter la video", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				setBounds(100, 100, 540, 290);
				setTitle("Menu Principal");
				contentPane.removeAll();
				affiche.removeAll();
				afficheimage.setIcon(null);
				afficheimage2.setIcon(null);
				contentPane.repaint();
				contentPane.add(twizyLogo);
				contentPane.add(btnAide);
				contentPane.add(btnModePas);
				contentPane.add(btnModeAutomatique);
				contentPane.add(renaultLogo);
				contentPane.add(ensemLogo);
				contentPane.add(btnModeVideo);
				contentPane.repaint();
				}
			}
		});
		
		bgr2gray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arretVideo=1;
				if(chargerImage==0){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Charger une image", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				Mat gray = CommonsFactory.bgr2gray(m);
				MatOfByte mat_of_byte = new MatOfByte();
				Highgui.imencode( ".png", gray, mat_of_byte );
				byte[] byte_array = mat_of_byte.toArray();
				BufferedImage buf_img = null;
				
				try {
					InputStream in = new ByteArrayInputStream( byte_array );
					buf_img = ImageIO.read( in );
					ImageIcon i = new ImageIcon(buf_img);
					Image image = i.getImage();
					Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					i = new ImageIcon(newimg);
					afficheimage2.setIcon(i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		bgr2hsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arretVideo=1;
				if(chargerImage==0){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Charger une image", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				Mat gray = CommonsFactory.bgr2hsv(m);
				MatOfByte mat_of_byte = new MatOfByte();
				Highgui.imencode( ".png", gray, mat_of_byte );
				byte[] byte_array = mat_of_byte.toArray();
				BufferedImage buf_img = null;
				
				try {
					InputStream in = new ByteArrayInputStream( byte_array );
					buf_img = ImageIO.read( in );
					ImageIcon i = new ImageIcon(buf_img);
					Image image = i.getImage();
					Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					i = new ImageIcon(newimg);
					afficheimage2.setIcon(i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		seuillage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arretVideo=1;
				if(chargerImage==0){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Charger une image", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				int nb_filter=3;
				ArrayList<Scalar> filter_list=new ArrayList<Scalar>();
				filter_list.add(new Scalar(0,30,30));
				filter_list.add(new Scalar(20,255,255));
				filter_list.add(new Scalar(160,50,50));
				filter_list.add(new Scalar(179,255,255));
				filter_list.add(new Scalar(105,150,40));
				filter_list.add(new Scalar(125,255,255));
				Mat mathsv=CommonsFactory.bgr2hsv(m);
				try {
					Mat matseuil=DetectingFactory.filterImageByColor(mathsv , nb_filter, filter_list );
					MatOfByte mat_of_byte = new MatOfByte();
					Highgui.imencode( ".png", matseuil, mat_of_byte );
					byte[] byte_array = mat_of_byte.toArray();
					BufferedImage buf_img = null;
					InputStream in = new ByteArrayInputStream( byte_array );
					buf_img = ImageIO.read( in );
					ImageIcon i = new ImageIcon(buf_img);
					Image image = i.getImage();
					Image newimg = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					i = new ImageIcon(newimg);
					afficheimage2.setIcon(i);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		});
		
		btnModeAutomatique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chargerImage=0;
				arretVideo=1;
				separator.setBounds(4, 50, 2, 401);
				separator1.setBounds(405, 50, 2, 401);
				separator2.setBounds(4, 49, 401, 2);
				separator3.setBounds(4, 450, 401, 2);
				setBounds(100, 100, 1000, 655);
				contentPane.remove(twizyLogo);
				contentPane.remove(btnAide);
				contentPane.remove(btnModePas);
				contentPane.remove(btnModeAutomatique);
				contentPane.remove(renaultLogo);
				contentPane.remove(ensemLogo);
				contentPane.remove(btnModeVideo);
				setTitle("Mode automatique");
				contentPane.add(btnChargerImage);
				contentPane.add(separator);
				contentPane.add(separator1);
				contentPane.add(separator2);
				contentPane.add(separator3);
				contentPane.add(separator4);
				contentPane.add(separator5);
				contentPane.add(separator6);
				contentPane.add(separator7);
				contentPane.add(separator8);
				contentPane.add(separator9);
				contentPane.add(separator10);
				contentPane.add(separator11);
				contentPane.add(traiterImage);
				contentPane.add(btnHome);
				contentPane.add(affiche);
				contentPane.add(afficheimage);
				contentPane.add(afficheimage2);
				contentPane.repaint();
				
			}
		});
		
		btnModePas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chargerImage=0;
				arretVideo=1;
				separator.setBounds(4, 50, 2, 401);
				separator1.setBounds(405, 50, 2, 401);
				separator2.setBounds(4, 49, 401, 2);
				separator3.setBounds(4, 450, 401, 2);
				setBounds(100, 100, 1000, 655);
				contentPane.remove(twizyLogo);
				contentPane.remove(btnAide);
				contentPane.remove(btnModePas);
				contentPane.remove(btnModeAutomatique);
				contentPane.remove(renaultLogo);
				contentPane.remove(ensemLogo);
				contentPane.remove(btnModeVideo);
				setTitle("Mode pas a pas");
				contentPane.add(btnChargerImage);
				contentPane.add(separator);
				contentPane.add(separator1);
				contentPane.add(separator2);
				contentPane.add(separator3);
				contentPane.add(separator4);
				contentPane.add(separator5);
				contentPane.add(separator6);
				contentPane.add(separator7);
				contentPane.add(separator8);
				contentPane.add(separator9);
				contentPane.add(separator10);
				contentPane.add(separator11);
				contentPane.add(bgr2gray);
				contentPane.add(bgr2hsv);
				traiterImage.setBounds(420, 250, 160, 35);
				contentPane.add(traiterImage);
				contentPane.add(seuillage);
				contentPane.add(btnHome);
				contentPane.add(affiche);
				contentPane.add(afficheimage);
				contentPane.add(afficheimage2);
				contentPane.repaint();
			}
		});
		
		btnModeVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chargerImage=0;
				arretVideo=1;
				setBounds(100, 100, 1000, 655);
				contentPane.remove(twizyLogo);
				contentPane.remove(btnAide);
				contentPane.remove(btnModePas);
				contentPane.remove(btnModeAutomatique);
				contentPane.remove(renaultLogo);
				contentPane.remove(ensemLogo);
				contentPane.remove(btnModeVideo);
				setTitle("Mode Video");
				separator.setBounds(235, 50, 2, 400);
				separator1.setBounds(735, 50, 2, 400);
				separator2.setBounds(235, 49, 501, 2);
				separator3.setBounds(235, 450, 501, 2);
				contentPane.add(separator);
				contentPane.add(separator1);
				contentPane.add(separator2);
				contentPane.add(separator3);
				contentPane.add(separator8);
				contentPane.add(separator9);
				contentPane.add(separator10);
				contentPane.add(separator11);
				contentPane.add(comboBox);
				contentPane.add(btnOk);
				contentPane.add(textChoix);
				contentPane.add(btnHome);
				contentPane.add(affiche);
				contentPane.repaint();
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().equals("Webcam")){
					    mode=1;
						arretVideo=0;
						contentPane.add(btnArret);
						contentPane.repaint();
				        contentPane.add(afficheVideo);
				        start(); 
				}
				if(comboBox.getSelectedItem().equals(" ")){
					JOptionPane jop10 = new JOptionPane();
					jop10.showMessageDialog(null, "Choisir un mode", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				if(comboBox.getSelectedItem().equals("Charger video")){
					contentPane.add(btnChargerVideo);
					contentPane.repaint();
				}
			}
		});
		
		btnArret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arretVideo=1;
				T.stop();
				contentPane.remove(afficheVideo);
				contentPane.remove(btnArret);
				contentPane.repaint();
			}
		});
		
		
	}
	
	public void start(){
		T=new Thread(this);
		T.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 Mat frame = new Mat();
	     Size sz = new Size(501, 400);
	     VideoCapture camera;
	     if(mode==1){
	     camera = new VideoCapture(0);
	     }
	     else{
	     camera = new VideoCapture(adresseVideo);	 
	     }
	     int echantillon=0;
	     while (true) {
	    	 echantillon++;
	         if (camera.read(frame)) {
	        	if(echantillon%10==0){ 
	          		DetectingControler d = new DetectingControler("red",frame);
	        		d.rgb2hsv();
					try {
						affiche.removeAll();
						d.filter();
						d.Boundaries();
						d.eliminateBoundaries();
						d.extract();
						Vector<Mat> toMatchVector = new Vector<Mat>();
						for( Mat image1 : d.matextract ) {
							toMatchVector.add( image1 );
						}
						MatchingControler2 test = new MatchingControler2( toMatchVector );
						test.matchAll();
						
						for(int i1=0;i1<d.matextract.size();i1++){
							Mat m3 = d.matextract.get(i1);
							MatOfByte mat_of_byte2 = new MatOfByte();
							Highgui.imencode( ".png", m3, mat_of_byte2 );
							byte[] byte_array2 = mat_of_byte2.toArray();
							BufferedImage buf_img2 = null;
							InputStream in2 = new ByteArrayInputStream( byte_array2 );
							buf_img2 = ImageIO.read( in2 );
							ImageIcon i2 = new ImageIcon(buf_img2);
							Image image2 = i2.getImage();
							Image newimg2 = image2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
							i2 = new ImageIcon(newimg2);
							
							final JLabel afficheimage10 = new JLabel("");
							afficheimage10.setBounds(5+i1, 5, 100, 100);
							afficheimage10.setIcon(i2);
							affiche.add(afficheimage10);
							
							final JLabel afficheimage4 = new JLabel(test.matchingDecision.get(i1));
							System.out.println("+++++++++++++++++++++++++++++ " + test.matchingDecision.get(i1));
							afficheimage4.setBounds(5+2*i1+35, 105, 100, 100);
							afficheimage4.setFont(new Font("Times New Roman", Font.BOLD, 24));
							affiche.add(afficheimage4);
							contentPane.add(afficheVideo);
							affiche.repaint();	
							contentPane.repaint();
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        	Imgproc.resize(frame, frame, sz);
                ImageIcon image;
				try {
					image = new ImageIcon(Mat2bufferedImage(frame));
		             afficheVideo.setIcon(image);
		             afficheVideo.repaint();
		             affiche.repaint();	
					 contentPane.repaint();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	        }
	}
	
    public static BufferedImage Mat2bufferedImage(Mat image) throws Exception {
        MatOfByte bytemat = new MatOfByte();
        Highgui.imencode(".jpg", image, bytemat);
		byte[] byte_array = bytemat.toArray();
		BufferedImage buf_img = null;
		InputStream in1 = new ByteArrayInputStream( byte_array );
        try {
            buf_img = ImageIO.read(in1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf_img;
    }


}
