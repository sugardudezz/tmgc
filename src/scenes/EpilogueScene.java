package scenes;

import scenes.SceneManager;
import utils.Geometry;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EpilogueScene extends JPanel {
    private JLabel label;
    private String[] messages = {
            "수많은 시련을 견뎌내고, 결국 당신은 최고의 다마고치를 키워냈다.",
            "전설 속에서만 존재하던 능력을 가진 그들의 모습은",
            "이제 현실이 되어, 당신과 함께 새로운 역사를 써 내려가고 있다.",
            "다마고치를 키우는 일은 더 이상 단순한 훈련이 아닌,",
            "깊은 유대와 신뢰의 여정이었고,",
            "당신은 그 길을 완벽하게 걸어왔다.",
            "마스터 브리더로서, 당신은 다마고치 세계의 전설이 되었다.",
            "이 세상에서 가장 강력하고 특별한 존재인 다마고치들과 함께,",
            "새로운 시대를 이끌어갈 준비가 되어 있나.",
            "당신의 여정은 끝나지 않았다.",
            "다마고치와 함께한 전설이 다른 이들에게 희망을 주며,",
            "또 다른 미래를 만들어갈 것이다."
    };
    private int messageIndex = 0;

    public EpilogueScene() {
        setLayout(null);

        label = new JLabel();
        label.setFont(new Font("Italic", Font.ITALIC, 20));
        label.setForeground(Color.BLACK);

        // JLabel의 텍스트 가운데 정렬
        label.setHorizontalAlignment(SwingConstants.CENTER);  // 수평 가운데 정렬
        label.setVerticalAlignment(SwingConstants.CENTER);  // 수직 가운데 정렬

        label.setBounds(new Geometry(0.5f, 0.5f, 0.6f, 0.3f));
        add(label);

        printEpilogueText();
    }

    private void printEpilogueText() {
        Timer timer = new Timer(1500, e -> {
            if (messageIndex < messages.length) {
                String currentMessage = messages[messageIndex];
                label.setText(currentMessage);
                messageIndex++;
            } else {
                ((Timer) e.getSource()).stop();  // 모든 메시지가 출력되면 타이머 종료
                SceneManager.playScene(new EndingScene());
            }
        });
        timer.start();
    }
}