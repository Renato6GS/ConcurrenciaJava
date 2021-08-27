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
    
    public boolean cliente;
    public static int clientes = 0;
    public static int aumentar = 0;
  
	public static boolean estadoMecanicos[] = new boolean[3]; // false = Está disponible, true = está trabajando
	
	public int clientesEnEspera = 0; // Esto es para el label de espera
	public static Object lock = new Object();
	static Random rand = new Random(System.nanoTime());
	ArrayList<Thread> hilos = new ArrayList<Thread>();
	Runnable runnable = null;
	public int nHilos;
	
	public TallerController() {
	}
	
	public TallerController(boolean cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		if(cliente) 
			esCliente();
		else 
			esMecanico();
	}
	
	private void esCliente() {
//		synchronized (lock) {
			bucle: while(true) {
				if(!estadoMecanicos[0] || !estadoMecanicos[1] || !estadoMecanicos[2]) {
					if(!estadoMecanicos[0]) { // false = disponible
						synchronized (lock) {
							estadoMecanicos[0] = !estadoMecanicos[0];
							mecanico = 0;
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
					else if(!estadoMecanicos[1]) {
						synchronized (lock) {
							estadoMecanicos[1] = !estadoMecanicos[1];
							mecanico = 1;
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
							mecanico = 2;
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
//					clientes = clientes != 0 ? clientes - 1 : clientes;
					break bucle;
				} else {
					try {
						int time = 0;
						synchronized (lock) {
							clientes++;
							int j = clientes;
							for (int i = 3; i < j; i+=3) {
//								aumentar = i % 3 == 0 ? aumentar + 1 : aumentar;
								aumentar = j % i == 0 ? aumentar + 1 : aumentar;
							}
							time = aumentar;
//							aumentar = clientes > 3 ? aumentar + 1 : 0;
//							clientes+=2;
//							aumentar = clientes == 6 ? aumentar + 1 : 0;
//							clientes--;
						}
						System.out.println(clientes);
//						Thread.sleep(3000 + (aumentar * 3000));
						Thread.sleep(3000 + (time * 3000));
						aumentar = aumentar > 0 ? aumentar - 1 : 0;
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
    	
    	Grafico j = new Grafico(1);
    	j.cambiar();
    }
    
    /**
     * Según el estado del mecánico, cambiará la imagen que se mostrará.
     * @param mecanico 
     * @param workSpace
     * @param estadoMecanico
     * @return Retorna un estado inverso.
     */
    public void cambiarMecanico(ImageView mecanico, ImageView workSpace, boolean estadoMecanico) {
    	String cambiarMecanico = estadoMecanico != false 
    			? "src/application/partials/MECANICO_COLOR.png"
    			: "src/application/partials/MECANICO_BLANCO.png";
    	
    	String cambiarWorkSpace = estadoMecanico != false 
    			? "src/application/partials/1.GRUA_CON_CARRO.png"
    			: "src/application/partials/2.GRUA_SIN_CARRO_OPCION1.png";
    	
    	File file = new File(cambiarMecanico);
		Image image = new Image(file.toURI().toString());
		mecanico.setImage(image);
		
		File file2 = new File(cambiarWorkSpace);
    	Image image2 = new Image(file2.toURI().toString());
    	workSpace.setImage(image2);
    	
//		return !estadoMecanico;
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
    
    public static int mecanico = 0;
    
    public class Grafico extends Thread {
    	
    	
    	public Grafico() {}
    	
    	public Grafico(int j) {
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	@Override
    	public void run() {
    		if (clientes > 0) {
    			try {
//					Thread.sleep(2000 + (clientes*500));
    				int value = aumentar;
    				System.out.println("hola desde clientes");
    				System.out.println("--- aumentar: " + aumentar);
//					Thread.sleep(3000 + (aumentar * 3000));
					Thread.sleep(3000 + (value * 3000));
//					clientes--;
					clientes = clientes > 0 ? clientes - 1 : 0;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		if (estadoMecanicos[0] && mecanico == 0) {
    			cambiosMecanico(estadoMecanicos[0], labelEstadoMecanico1, clienteAtendido1, mecanico1, workSpace1, autoListo1);
    		} else if (estadoMecanicos[1] && mecanico == 1) {
    			cambiosMecanico(estadoMecanicos[1], labelEstadoMecanico2, clienteAtendido2, mecanico2, workSpace2, autoListo2);
    		} else if (estadoMecanicos[2] && mecanico == 2) {
    			cambiosMecanico(estadoMecanicos[2], labelEstadoMecanico3, clienteAtendido3, mecanico3, workSpace3, autoListo3);
    		} 
    	}
    	
    	public void cambiar() {
    		System.out.println(clientes);
    		if (clientes > 0) {
    			System.out.println("hola 1");
    			Grafico h4 = new Grafico();
    			h4.start();
    		}
    		
    		if (estadoMecanicos[0] && mecanico == 0) {
    			Grafico h1 = new Grafico();
    			h1.start();
    		} else if (estadoMecanicos[1] && mecanico == 1) {
    			Grafico h2 = new Grafico();
    			h2.start();
    		} else if (estadoMecanicos[2] && mecanico == 2) {
    			Grafico h3 = new Grafico();
    			h3.start();
    		} 
    	}
    	
    	public void cambiosMecanico(boolean originalState, Label estadoMecanico, ImageView cliente, ImageView mecanico, ImageView workSpace, ImageView autoListo) {
//    		boolean originalState = estadoMecanicos[0];
    		
    		Platform.runLater(() -> {
    			estadoMecanico.setText("Atendiendo");
    			cliente.setVisible(true);
				cambiarMecanico(mecanico, workSpace, originalState);
			});
    		
    		try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		Platform.runLater(() -> {
    			estadoMecanico.setText("Esperando");
    			cliente.setVisible(false);
				cambiarMecanico(mecanico, workSpace, !originalState);
				
//				Esta es la lógica para aparecer el auto y que se anime a la derecha. Solo funciona por ahora en el 1
				autoListo.setVisible(true);
				TranslateTransition tt = new TranslateTransition(Duration.millis(2000), autoListo);
				tt.setToX(400f);
				tt.play();
				
    		});
    		
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		Platform.runLater(() -> {
//				Animación para retornar:
    			autoListo.setVisible(false);
				TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), autoListo);
				tt2.setToX(0f);
				tt2.play();
    		});
    	}
    }
}
