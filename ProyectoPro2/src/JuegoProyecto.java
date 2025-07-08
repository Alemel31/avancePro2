import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.File;



public class JuegoProyecto extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Rectangle jugador;
    private ArrayList<Rectangle> enemigosCuadrados;
    private Random rand;

    private boolean gameOver = false;

    private BufferedImage imagenFondo;
    private BufferedImage spaceshipSprite;
    private BufferedImage moonRocksSprite;

    private ArrayList<Rectangle> balas;

    public JuegoProyecto() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 400));

        //setBackground(Color.WHITE);
        cargarImagenFondo();
        cargarPersonajes();

        reiniciarJuego();

        balas = new ArrayList<>();

        timer = new Timer(20, this);
        timer.start();

        addKeyListener(this);
    }


    private void reiniciarJuego() {
        jugador = new Rectangle(50, 150, 60, 60);
        enemigosCuadrados = new ArrayList<>();
        rand = new Random();
        gameOver = false;
    }
    private void cargarImagenFondo() {
        try {
            File imageFile = new File("C:\\Users\\hp\\IdeaProjects\\ProyectoPro2\\src\\resources\\backgroundSpace.png");
            if (imageFile.exists()) {
                imagenFondo = ImageIO.read(imageFile);
                System.out.println("Imagen cargada desde: " + imageFile.getAbsolutePath());
            } else {
                System.out.println("No se encontró background.png en la raíz del proyecto");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    private BufferedImage cargarImagen(String ruta) {
        try {
            File imageFile = new File(ruta);
            if (imageFile.exists()) {
                return ImageIO.read(imageFile);
            } else {
                System.out.println("No se encontró la imagen en: " + ruta);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }

    private void cargarPersonajes() {
        try {
            // Cargar personajes de la nave del jugador
            spaceshipSprite = cargarImagen("C:\\Users\\hp\\IdeaProjects\\ProyectoPro2\\src\\resources\\spaceShip-removebg-preview.png");
            moonRocksSprite = cargarImagen("C:\\Users\\hp\\IdeaProjects\\ProyectoPro2\\src\\resources\\moonRocks-removebg-preview.png");

            if (spaceshipSprite != null) {
                System.out.println("Sprites cargados correctamente");
            } else {
                System.out.println("No se pudieron cargar los sprites");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los sprites: " + e.getMessage());
        }
    }

    // balas de la nave

    private void moverBalas() {
        Iterator<Rectangle> it = balas.iterator();
        while (it.hasNext()) {
            Rectangle bala = it.next();
            bala.x += 5;
            if (bala.x > getWidth()) {
                it.remove();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            moverEnemigos();
            checkCollision();
            crearEnemigos();
            moverBalas();
            repaint();
        }
    }
    private void moverEnemigos() {
        Iterator<Rectangle> it = enemigosCuadrados.iterator();
        while (it.hasNext()) {
            Rectangle enemigos = it.next();
            enemigos.x -= 5;
            if (enemigos.x + enemigos.width < 0) {
                it.remove();
            }
        }
    }
    private void crearEnemigos() {
        if (rand.nextInt(100) < 3) { // ajusta frecuencia
            int y = rand.nextInt(getHeight() - 40);
            enemigosCuadrados.add(new Rectangle(getWidth(), y, 70, 50));
        }
    }

    private void checkCollision() {
        // Verificar colisión jugador-enemigo
        for (Rectangle enemigo : new ArrayList<>(enemigosCuadrados)) {
            if (jugador.intersects(enemigo)) {
                gameOver = true;
                timer.stop();
                break;
            }
            // Verificar colisión bala-enemigo
            for (Rectangle bala : new ArrayList<>(balas)) {
                if (bala.intersects(enemigo)) {
                    // Eliminar el enemigo y la bala
                    enemigosCuadrados.remove(enemigo);
                    balas.remove(bala);

                    break;  // Salir del bucle de balas si hay colisión
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    //FOndo de imagen
    // 1. Dibujar el fondo primero
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Si no hay imagen de fondo, usar un color de fondo
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

    // Jugador
        if (spaceshipSprite != null) {
            g.drawImage(spaceshipSprite, jugador.x, jugador.y, jugador.width, jugador.height, this);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(jugador.x, jugador.y, jugador.width, jugador.height);
        }

    // Enemigos
        if (moonRocksSprite != null) {
            for (Rectangle enemigos : enemigosCuadrados) {
                g.drawImage(moonRocksSprite, enemigos.x, enemigos.y, enemigos.width, enemigos.height, this);
            }
        } else {
            g.setColor(Color.RED);
            for (Rectangle enemigos : enemigosCuadrados) {
                g.fillOval(enemigos.x, enemigos.y, enemigos.width, enemigos.height);
            }
        }

    // Balas - Amarillo para balas
        g.setColor(Color.GREEN);
        for (Rectangle bala : balas) {
            g.fillRect(bala.x, bala.y, bala.width, bala.height);
        }

    // Fin del juego
    if (gameOver) {
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("¡Perdiste el juego!", 250, getHeight() / 2);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int velocidad = 10;

        if (!gameOver) {
            if (key == KeyEvent.VK_UP && jugador.y - velocidad >= 0)
                jugador.y -= velocidad;
            else if (key == KeyEvent.VK_DOWN && jugador.y + velocidad + jugador.height <= getHeight())
                jugador.y += velocidad;
            else if (key == KeyEvent.VK_LEFT && jugador.x - velocidad >= 0)
                jugador.x -= velocidad;
            else if (key == KeyEvent.VK_RIGHT && jugador.x + velocidad + jugador.width <= getWidth())
                jugador.x += velocidad;

            //tecla de disparo SPACE
            else if (key == KeyEvent.VK_SPACE) {
                balas.add(new Rectangle(jugador.x + jugador.width, jugador.y + jugador.height / 2, 10, 10));
            }

            //Tecla de reinicio del juego R
            } else if (key == KeyEvent.VK_R) {
            reiniciarJuego();   // reinicia variables
            timer.start(); // vuelve a arrancar el juego
            repaint();
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {
    JFrame frame = new JFrame("Juego de Colisión - Esquiva los enemigos");
    JuegoProyecto juegoProyecto = new JuegoProyecto();
    frame.add(juegoProyecto);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }
}

