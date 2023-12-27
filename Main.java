import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private JTextField lengthField, constantField;
    private JButton startButton, stopButton;
    private JPanel panel;
    private boolean isRunning = false;
    private Timer timer;
    private int x = 0;

    public Main() {
        setTitle("Гармонические колебания");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        JLabel lengthLabel = new JLabel("Длина:"); // Создаем поля для ввода значений
        lengthField = new JTextField("100", 10);

        JLabel constantLabel = new JLabel("Конст:");
        constantField = new JTextField("1", 10);

        startButton = new JButton("Старт"); // Создаем кнопки
        startButton.addActionListener(this);

        stopButton = new JButton("Стоп");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);


        panel = new JPanel(); // Создаем панель для размещения элементов управления
        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(constantLabel);
        panel.add(constantField);
        panel.add(startButton);
        panel.add(stopButton);

        add(panel); // Добавляем панель на форму

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startAnimation();
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        } else if (e.getSource() == stopButton) {
            stopAnimation();
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void startAnimation() {
        isRunning = true;
        x = 0;
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    return;
                }

                int length = Integer.parseInt(lengthField.getText());
                double constant = Double.parseDouble(constantField.getText());
                double distance = length * (1 + Math.cos(constant * x)) / 2;

                Graphics g = panel.getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
                g.setColor(Color.RED);
                g.fillOval((int) distance, 100, 10, 10);

                x++;
            }
        });
        timer.start();
    }

    private void stopAnimation() {
        isRunning = false;
        timer.stop();
    }

    public static void main(String[] args) {
        new Main();
    }
}
