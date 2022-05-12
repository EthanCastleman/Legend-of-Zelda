/*
	Name: Ethan Castleman
	Date: 3/29/2022
	Description: Game.java
*/
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;
	MySoundClip clip;

	public Game() throws Exception
	{
		model = new Model();
		controller = new Controller(model);
	 	view = new View(controller, model);
		clip = new MySoundClip("04_Labyrinth.WAV", 1);
		clip.play();
		view.addMouseListener(controller);
		this.setTitle("A5 - Sprites, Pots and Boomerangs");
		this.setSize(800, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
		controller.loadFile();
	}

	public void run()
{
	while(true)
	{
		controller.update();
		model.update();
		view.repaint(); // Indirectly calls View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 45 miliseconds
		try
		{
			Thread.sleep(45);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

	public static void main(String[] args) throws Exception
	{
		Game g = new Game();
		g.run();
	}
}
