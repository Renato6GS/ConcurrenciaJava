package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TallerController implements Runnable {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView workSpace3;

    @FXML
    private ImageView workSpace2;

    @FXML
    private ImageView workSpace1;

    @FXML
    private ImageView mecanico3;

    @FXML
    private ImageView mecanico2;

    @FXML
    private ImageView mecanico1;

    @FXML
    private ImageView clienteEsperando4;

    @FXML
    private ImageView clienteEsperando3;

    @FXML
    private ImageView clienteEsperando2;

    @FXML
    private ImageView clienteEsperando1;

    @FXML
    private ImageView clienteAtendido3;

    @FXML
    private ImageView clienteAtendido2;

    @FXML
    private ImageView clienteAtendido1;

    @FXML
    private Button btnAgregar;

    @FXML
    private Label labelClientesEnEspera;

    @FXML
    private Label labelEstadoMecanico3;

    @FXML
    private Label labelEstadoMecanico2;

    @FXML
    private Label labelEstadoMecanico1;

    @FXML
    private ImageView autoListo1;

    @FXML
    private ImageView autoListo2;

    @FXML
    private ImageView autoListo3;
    
    private void texto() {
    	labelEstadoMecanico1.setText("Atendiendo");
    }
    
    private boolean cliente;
    private static int clientes = 1;
  
	private static boolean estadoMecanicos[] = new boolean[3]; // false = Está disponible, true = está trabajando
	
	private int clientesEnEspera = 0; // Esto es para el label de espera
	private static Object lock = new Object();
	static Random rand = new Random(System.nanoTime());
	ArrayList<Thread> hilos = new ArrayList<Thread>();
	Runnable runnable = null;
	private int nHilos;
	
	public TallerController() {}
	
	public TallerController(boolean cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
//		Runnable updater = new Runnable() {
//			@Override
//			public void run() {
//				texto();
//			}
//		};
		if(cliente) 
			esCliente();
		else 
			esMecanico();
//		Platform.runLater(updater);
		
	}
	
	private void esCliente() {
//		synchronized (lock) {
			bucle: while(true) {
				if(!estadoMecanicos[0] || !estadoMecanicos[1] || !estadoMecanicos[2]) {
					if(!estadoMecanicos[0]) { // false = disponible
						synchronized (lock) {
							estadoMecanicos[0] = !estadoMecanicos[0];
							System.out.println("Estoy con el mecanico 1");
						}
//						estadoMecanicos[0] = cambiarMecanico(mecanico1, workSpace1, estadoMecanicos[0]);
//						clienteAtendido1.setVisible(true);
//						Runnable updater = new Runnable() {
//							@Override
//							public void run() {
//								texto();
//							}
//						};
						Platform.runLater(() -> {
							labelEstadoMecanico1.setText("holaa");
							System.out.println("holaaaa");
						});
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//						lock.notifyAll();
						synchronized (lock) {
							lock.notifyAll();
						}
//						Platform.runLater(updater);
//						
//						break bucle;
					}
					else if(!estadoMecanicos[1]) {
						synchronized (lock) {
							estadoMecanicos[1] = !estadoMecanicos[1];
							System.out.println("Estoy con el mecanico 2");
//							lock.notifyAll();
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (lock) {
							lock.notifyAll();
						}
					}
					else if(!estadoMecanicos[2]) {
						synchronized (lock) {
							estadoMecanicos[2] = !estadoMecanicos[2];
							System.out.println("Estoy con el mecanico 3");
						}
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						synchronized (lock) {
							lock.notifyAll();
						}
					}
					break bucle;
				} else {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//		}
	}
	
	private void esMecanico() {
		synchronized (lock) {
			while(true) {
//				if(estadoMecanicos[0]) {
//					try {
//						Thread.sleep(2000);
//						labelEstadoMecanico1.setText("Esperando");
//						clienteAtendido1.setVisible(false);
//						autoListo1.setVisible(true);
//						TranslateTransition tt = new TranslateTransition(Duration.millis(2000), autoListo1);
//						tt.setToX(400f);
//						tt.play();
////						EL TIEMPO QUE TARDA EL MECÁNICO DEBE SER MAYOR A 2 SEGUNDOS SÍ O SÍ
//						if (!estadoMecanicos[0]) {
//							autoListo1.setVisible(false);
//							TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), autoListo1);
//							tt2.setToX(0f);
//							tt2.play();
//						}
//						estadoMecanicos[0] = !estadoMecanicos[0];
//						lock.notifyAll();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				if(estadoMecanicos[1]) {
//					try {
//						Thread.sleep(2000);
//						labelEstadoMecanico2.setText("Esperando");
//						clienteAtendido2.setVisible(false);
//						autoListo2.setVisible(true);
//						TranslateTransition tt = new TranslateTransition(Duration.millis(2000), autoListo2);
//						tt.setToX(400f);
//						tt.play();
////						EL TIEMPO QUE TARDA EL MECÁNICO DEBE SER MAYOR A 2 SEGUNDOS SÍ O SÍ
//						if (!estadoMecanicos[1]) {
//							autoListo2.setVisible(false);
//							TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), autoListo2);
//							tt2.setToX(0f);
//							tt2.play();
//						}
//						estadoMecanicos[1] = !estadoMecanicos[1];
//						lock.notifyAll();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				if(estadoMecanicos[2]) {
//					try {
//						Thread.sleep(2000);
//						labelEstadoMecanico1.setText("Esperando");
//						clienteAtendido1.setVisible(false);
//						autoListo1.setVisible(true);
//						TranslateTransition tt = new TranslateTransition(Duration.millis(2000), autoListo3);
//						tt.setToX(400f);
//						tt.play();
////						EL TIEMPO QUE TARDA EL MECÁNICO DEBE SER MAYOR A 2 SEGUNDOS SÍ O SÍ
//						if (!estadoMecanicos[2]) {
//							autoListo3.setVisible(false);
//							TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), autoListo3);
//							tt2.setToX(0f);
//							tt2.play();
//						}
//						estadoMecanicos[2] = !estadoMecanicos[2];
//						lock.notifyAll();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
				if(estadoMecanicos[0]) {
					System.out.println("Mecanico 1, estoy disponible.");
					estadoMecanicos[0] = !estadoMecanicos[0];
//					lock.notifyAll();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(estadoMecanicos[1]) {
					System.out.println("Mecanico 2, estoy disponible.");
					estadoMecanicos[1] = !estadoMecanicos[1];
//					lock.notifyAll();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(estadoMecanicos[2]) {
					System.out.println("Mecanico 3, estoy disponible.");
					estadoMecanicos[2] = !estadoMecanicos[2];
//					lock.notifyAll();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void cambiar() {
		Platform.runLater(() -> {
			texto();
		});
	}

    @FXML
    void clickBtnAgregar(ActionEvent event) throws InterruptedException {
    	
    	nHilos = hilos.size(); // Para iniciar (start()) un nuevo cliente
    	runnable = new TallerController(true); // Cliente
    	hilos.add(new Thread(runnable));
    	hilos.get(nHilos).start();
    
//    	hilos.add(new Thread(() -> {
//    		Platform.runLater(() -> {
//    			texto();
//    		});
//    	}));
    	
//		estadoMecanicos[0] = cambiarMecanico(mecanico1, workSpace1, estadoMecanicos[0]);
//		estadoMecanicos[1] = cambiarMecanico(mecanico2, workSpace2, estadoMecanicos[1]);
//		estadoMecanicos[2] = cambiarMecanico(mecanico3, workSpace3, estadoMecanicos[2]);

		
//		Esta es la lógica para aparecer el auto y que se anime a la derecha. Solo funciona por ahora en el 1
//		autoListo1.setVisible(true);
//		TranslateTransition tt = new TranslateTransition(Duration.millis(2000), autoListo1);
//		tt.setToX(400f);
//		tt.play();
////		EL TIEMPO QUE TARDA EL MECÁNICO DEBE SER MAYOR A 2 SEGUNDOS SÍ O SÍ
//		if (!estadoMecanicos[0]) {
//			autoListo1.setVisible(false);
//			TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000), autoListo1);
//			tt2.setToX(0f);
//			tt2.play();
//		}
    }
    
    /**
     * Según el estado del mecánico, cambiará la imagen que se mostrará.
     * @param mecanico 
     * @param workSpace
     * @param estadoMecanico
     * @return Retorna un estado inverso.
     */
    private boolean cambiarMecanico(ImageView mecanico, ImageView workSpace, boolean estadoMecanico) {
    	String cambiarMecanico = estadoMecanico != true 
    			? "src/application/partials/MECANICO_COLOR.png"
    			: "src/application/partials/MECANICO_BLANCO.png";
    	
    	String cambiarWorkSpace = estadoMecanico != true 
    			? "src/application/partials/1.GRUA_CON_CARRO.png"
    			: "src/application/partials/2.GRUA_SIN_CARRO_OPCION1.png";
    	
    	File file = new File(cambiarMecanico);
		Image image = new Image(file.toURI().toString());
		mecanico.setImage(image);
		
		File file2 = new File(cambiarWorkSpace);
    	Image image2 = new Image(file2.toURI().toString());
    	workSpace.setImage(image2);
    	
		return !estadoMecanico;
    }

    @FXML
    void initialize() {
//    	Le ponemos como estado a los mecánicos de falso para que indiquen que están disponibles
    	estadoMecanicos[0] = false;
		estadoMecanicos[1] = false;
		estadoMecanicos[2] = false;
		
//		Solo habrá 1 hilo encargándose de evaluar si los mecanicos están libres o no
		runnable = new TallerController(false);
		hilos.add(new Thread(runnable));
		hilos.get(0).start();
		
        clienteAtendido1.setVisible(false);
        clienteAtendido2.setVisible(false);
        clienteAtendido3.setVisible(false);
        labelClientesEnEspera.setVisible(false);
        clienteEsperando1.setVisible(false);
        clienteEsperando2.setVisible(false);
        clienteEsperando3.setVisible(false);
        clienteEsperando4.setVisible(false);
        autoListo1.setVisible(false);
        autoListo2.setVisible(false);
        autoListo3.setVisible(false);
        labelEstadoMecanico1.setText("Esperando");
        labelEstadoMecanico2.setText("Esperando");
        labelEstadoMecanico3.setText("Esperando");
    }
}
