package jdumper.ui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jdumper.JDCaptor;
import jdumper.JDStatisticsTakerLoader;
import jdumper.JpcapDumper;
import jdumper.stat.JDStatisticsTaker;

public class JDFrame extends JFrame implements ActionListener
{
	public JDCaptor captor;
	
	JLabel statusLabel;
	JMenuItem captureMenu,stopMenu;
	JMenu statMenu;
	JButton captureButton,stopButton;
	
	public JDTablePane tablePane;

	public static JDFrame openNewWindow(JDCaptor captor){
		JDFrame frame=new JDFrame(captor);
		frame.setVisible(true);
		
		return frame;
	}

	public JDFrame(JDCaptor captor){
		this.captor=captor;
		tablePane=new JDTablePane(captor);
		captor.setJDFrame(this);
		
		setTitle("Analisador de Pacotes");

		// Create Menu
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);

		//Capture Menu
		JMenu menu=new JMenu("Capturar");
		menuBar.add(menu);
		captureMenu=new JMenuItem("Iniciar");
		captureMenu.setIcon(getImageIcon("/image/capture.gif"));
		captureMenu.setActionCommand("Iniciar");
		captureMenu.addActionListener(this);
		menu.add(captureMenu);
		stopMenu=new JMenuItem("Parar");
		stopMenu.setIcon(getImageIcon("/image/stopcap.gif"));
		stopMenu.setActionCommand("Parar");
		stopMenu.addActionListener(this);
		stopMenu.setEnabled(false);
		menu.add(stopMenu);
		
		//Stat Menu
		statMenu=new JMenu("Graficos");
		menuBar.add(statMenu);
		menu=new JMenu("Tipos");
		statMenu.add(menu);
		java.util.List<JDStatisticsTaker> stakers=JDStatisticsTakerLoader.getStatisticsTakers();
		for(int i=1;i<stakers.size()-1;i++){
			JMenuItem item=new JMenuItem(stakers.get(i).getName());
			item.setActionCommand("CUMSTAT"+i);
			item.addActionListener(this);
			menu.add(item);
		}
		
		//Create Toolbar
		JToolBar toolbar=new JToolBar();
		toolbar.setFloatable(false);
		captureButton=new JButton(getImageIcon("/image/capture.gif"));
		captureButton.setActionCommand("Iniciar");
		captureButton.addActionListener(this);
		toolbar.add(captureButton);
		stopButton=new JButton(getImageIcon("/image/stopcap.gif"));
		stopButton.setActionCommand("Parar");
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		toolbar.add(stopButton);
		
		statusLabel=new JLabel("JpcapDumper started.");
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(statusLabel,BorderLayout.SOUTH);
		getContentPane().add(tablePane,BorderLayout.CENTER);
		getContentPane().add(toolbar,BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				saveProperty();
				JpcapDumper.closeWindow((JDFrame)evt.getSource());
			}
		});
		
		loadProperty();
	}
	
	public void actionPerformed(ActionEvent evt){
		String cmd=evt.getActionCommand();
		
		if(cmd.equals("Iniciar")){
			captor.capturePacketsFromDevice();
		}else if(cmd.equals("Parar")){
			captor.stopCapture();
		}else if(cmd.startsWith("CUMSTAT")){
			int index=Integer.parseInt(cmd.substring(7));
			captor.addCumulativeStatFrame(JDStatisticsTakerLoader.getStatisticsTakerAt(index));
		}else if(cmd.startsWith("CONSTAT")){
			int index=Integer.parseInt(cmd.substring(7));
			captor.addContinuousStatFrame(JDStatisticsTakerLoader.getStatisticsTakerAt(index));
		}
	}
	
	public void clear(){
		tablePane.clear();
	}

	public void startUpdating(){
		JDFrameUpdater.setRepeats(true);
		JDFrameUpdater.start();
	}
	
	public void stopUpdating(){
		JDFrameUpdater.stop();
		JDFrameUpdater.setRepeats(false);
		JDFrameUpdater.start();
	}

	javax.swing.Timer JDFrameUpdater=new javax.swing.Timer(500,new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			tablePane.fireTableChanged();
			statusLabel.setText("Captured "+captor.getPackets().size()+" packets.");

			repaint();
		}
	});

	void loadProperty(){
		setSize(Integer.parseInt(JpcapDumper.preferences.get("WinWidth","640")),
		        Integer.parseInt(JpcapDumper.preferences.get("WinHeight","480")));
		setLocation(Integer.parseInt(JpcapDumper.preferences.get("WinX","0")),
			Integer.parseInt(JpcapDumper.preferences.get("WinY","0")));
	}
	
	void saveProperty()
	{
		JpcapDumper.preferences.put("WinWidth",String.valueOf(getBounds().width));
		JpcapDumper.preferences.put("WinHeight",String.valueOf(getBounds().height));
		JpcapDumper.preferences.put("WinX",String.valueOf(getBounds().x));
		JpcapDumper.preferences.put("WinY",String.valueOf(getBounds().y));
		
		tablePane.saveProperty();
		
		JpcapDumper.saveProperty();
	}
	
	public void enableCapture(){
		captureMenu.setEnabled(true);
		captureButton.setEnabled(true);
		stopMenu.setEnabled(false);
		stopButton.setEnabled(false);
	}
	
	public void disableCapture(){
		captureMenu.setEnabled(false);
		captureButton.setEnabled(false);
		stopMenu.setEnabled(true);
		stopButton.setEnabled(true);
	}
	
	private ImageIcon getImageIcon(String path){
		return new ImageIcon(this.getClass().getResource(path));
	}
}
