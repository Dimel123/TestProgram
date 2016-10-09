import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class MainWindow extends JFrame {

    private FilesSearching filesSearching = new FilesSearching();
    private JTabbedPane jTabbedPane;
    private JPanel mergeFile;
    private JPanel searchFile;
    private JMenuBar menuBar;
    private JMenu startMenu;
    private JMenu optionsMenu;
    private JLabel whatSearch;
    private JTextArea textArea;
    private JScrollPane scrollWhatSearch;
    private JList jList;
    private SearchListModel model;
    private JScrollPane scrollModel;
    private JButton btnChoise;
    private File file;
    private JButton openFile;


    public MainWindow(String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 800, 20);
        startMenu = new JMenu("Файл");
        optionsMenu = new JMenu("Опции");
        menuBar.add(startMenu);
        menuBar.add(optionsMenu);
        getContentPane().add(menuBar);

        jTabbedPane = new JTabbedPane();
        mergeFile = new JPanel(null);
        searchFile = new JPanel(null);
        jTabbedPane.addTab("Слияние", mergeFile);
        jTabbedPane.addTab("Поиск", searchFile);
        jTabbedPane.setBounds(10, 50, 780, 500);
        getContentPane().add(jTabbedPane);

        whatSearch = new JLabel("Что ищем?");
        whatSearch.setBounds(10, 10, 80, 30);
        searchFile.add(whatSearch);
        textArea = new JTextArea();
        textArea.setText("Введите текст");
        scrollWhatSearch = new JScrollPane();
        scrollWhatSearch.setViewportView(textArea);
        scrollWhatSearch.setBounds(10, 40, 390, 260);
        searchFile.add(scrollWhatSearch);

        model = new SearchListModel();
        jList = new JList(model);
        scrollModel = new JScrollPane();
        scrollModel.setBounds(420, 40, 350, 260);
        scrollModel.setViewportView(jList);
        searchFile.add(scrollModel);

        btnChoise = new JButton("Выбрать директорию поиска");
        btnChoise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                String  text = textArea.getText();
                if(text.equals("Введите текст")){
                    JOptionPane.showMessageDialog(null, "Введите текст для поиска");
                }else{
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                int ch = chooser.showOpenDialog(null);
                if(ch==JFileChooser.APPROVE_OPTION){
                    try {
                        model.removeAllElements();
                        String path = chooser.getSelectedFile().getPath();
                        model.addAllElements(filesSearching.printAllFilesContentWord(path, text));
                        if(model.getSize()==0){
                            model.addElement("Совпадений не найдено");
                        }
                        jList.updateUI();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            }
        });
        btnChoise.setBounds(20, 320, 220, 20);
        searchFile.add(btnChoise);

        openFile = new JButton("Открыть файл");
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> fileNames = jList.getSelectedValuesList();
                for(String s: fileNames){
                    // здесь надо открыть файл
                }
            }
        });
        openFile.setBounds(480, 320, 220, 20);
        searchFile.add(openFile);


        setVisible(true);
    }

    public void addElement(String s){
        model.addElement(s);
    }

    public void removeElement(int index){
        model.removeElement(index);
    }

    public void updateUI(){
        jList.updateUI();
    }

}
