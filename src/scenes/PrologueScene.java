package scenes;

import scenes.SceneManager;
import utils.Geometry;
import utils.BackgroundImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PrologueScene extends JPanel {
    String imagePath = "./src/images/";

    private JLabel label;
    private String[] messages = {
            "어느 날, 세상에 나타난 특별한 존재들.",
            "동물과 닮았지만, 그 이상의 능력을 가진 다마고치(たまごっち)다.",
            "이들은 자신을 키우는 '다마고치 브리더'를 깊이 따르며,",
            "그들의 충성심과 능력을 통해 전설적인 존재로 거듭난다.",
            "이제,  운명은 당신의 손에 달렸다.",
            "최고의 다마고치를 키워 마스터 브리더의 길을 걸어가라.",
            "새로운 시대의 시작, 그 여정이 바로 지금부터 펼쳐진다 !",
    };
    private int messageIndex = 0;

    public PrologueScene() {
        setLayout(null);

        label = new JLabel();
        label.setFont(new Font("Italic", Font.ITALIC, 20));
        label.setForeground(Color.BLACK);

        // JLabel의 텍스트 가운데 정렬
        label.setHorizontalAlignment(SwingConstants.CENTER);  // 수평 가운데 정렬
        label.setVerticalAlignment(SwingConstants.CENTER);  // 수직 가운데 정렬

        label.setBounds(new Geometry(0.5f, 0.5f, 0.6f, 0.3f));
        add(label);

        printPrologueText();
        BackgroundImage bg = new BackgroundImage(imagePath + "RoundScene.png");
        add(bg);
    }

    private void printPrologueText() {
        Timer timer = new Timer(1000, e -> {
            if (messageIndex < messages.length) {
                String currentMessage = messages[messageIndex];
                label.setText(currentMessage);
                messageIndex++;
            } else {
                ((Timer) e.getSource()).stop();  // 모든 메시지가 출력되면 타이머 종료
                SceneManager.playScene(new ChooseTamagochi());
            }
        });
        timer.start();
    }
}