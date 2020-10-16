package solution1.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class SimpleWindow implements UserInterface {
	private JFrame window;
	private JTextArea text;
	private JTextField inString;
	private JButton go;
	private JPanel sPanel;
	private BlockingQueue<String> mq;
	
	public SimpleWindow(String title){
		window = new JFrame(title);
		window.setLayout(new BorderLayout());
		text = new JTextArea();
		text.setEditable(false);
		text.setBackground(new Color(255,220,220));
		text.setForeground(Color.BLUE);
		text.setFont(new Font(Font.MONOSPACED,Font.BOLD, 18));
		window.add(new JScrollPane(text), BorderLayout.CENTER);
		sPanel = new JPanel();
		sPanel.setLayout(new BorderLayout());
		window.add(sPanel,BorderLayout.SOUTH);
		inString = new JTextField();
		inString.setFont(new Font("Sansserif",Font.BOLD, 18));
		inString.requestFocusInWindow();
		go = new JButton("Send");
		go.setForeground(Color.RED.darker());
		go.setBackground(Color.WHITE);
		mq = new ArrayBlockingQueue<String>(100);
		ActionListener goAction = new GoListener();
		go.addActionListener(goAction);
		inString.registerKeyboardAction(goAction, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		sPanel.add(inString,BorderLayout.CENTER);
		sPanel.add(go,BorderLayout.EAST);
		window.setSize(550,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationByPlatform(true);
		window.setVisible(true);
	}
	
	class GoListener implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			try {
				mq.put(inString.getText());
				inString.setText("");
				inString.requestFocusInWindow();
			} catch (InterruptedException e1) {
					e1.printStackTrace();
			}
		}		
	}
	
	@Override
	public String getString(){
		
		try {
			return mq.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "Should not happen";
		}
	}
	
	@Override
	public void addString(String s){
		text.append(s);
	}
	
	@Override
	public void clear(){
		text.setText("");
	}
	
	@Override
	public void exit() {
		window.dispose();
		System.exit(0);
	}

	@Override
	public boolean continuePrompt(int nGuess) {
		return (0 == JOptionPane.showConfirmDialog(null
				, "Correct, it took " + nGuess + " guesses\nContinue?"));
	}


}
