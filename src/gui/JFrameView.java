package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.filechooser.FileNameExtensionFilter;

import model.AbstractModel;
import model.Model;
import model.Pixel;
import view.IMEView;

/**
 * GUI class that implements the view. This class will use Java Swing to help get input from the
 * user, and then output to the user.
 */
public class JFrameView extends JFrame implements IMEView, ActionListener {
  private AbstractModel model = new Model();
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  /**
   * initializes JFrameView object.
   */
  public JFrameView() {
    super();
    JPanel mainPanel;
    JScrollPane mainScrollPane;
    setTitle("Swing features");
    setSize(400, 400);


    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //Histogram
    JTextArea textArea = new JTextArea(10, 20);
    textArea.setBorder(BorderFactory.createTitledBorder("Histogram"));

    mainPanel.add(textArea);

    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    String[] images = {"res/koala-horizontal.png"};
    JLabel[] imageLabel = new JLabel[images.length];
    JScrollPane[] imageScrollPane = new JScrollPane[images.length];

    for (int i = 0; i < imageLabel.length; i++) {
      imageLabel[i] = new JLabel();
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageLabel[i].setIcon(new ImageIcon(images[i]));
      imageScrollPane[i].setPreferredSize(new Dimension(100, 600));
      imagePanel.add(imageScrollPane[i]);
    }

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //color chooser
    JPanel colorChooserPanel = new JPanel();
    colorChooserPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(colorChooserPanel);
    JButton colorChooserButton = new JButton("Choose a color");
    colorChooserButton.setActionCommand("Color chooser");
    colorChooserButton.addActionListener(this);
    colorChooserPanel.add(colorChooserButton);
    colorChooserDisplay = new JLabel("      ");
    colorChooserDisplay.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.WHITE);
    colorChooserPanel.add(colorChooserDisplay);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    //JOptionsPane message dialog
    JPanel messageDialogPanel = new JPanel();
    messageDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(messageDialogPanel);

    JButton messageButton = new JButton("Luma");
    messageButton.setActionCommand("Luma");
    messageButton.addActionListener(this);
    messageDialogPanel.add(messageButton);

    //JOptionsPane input dialog
    JPanel inputDialogPanel = new JPanel();
    inputDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(inputDialogPanel);

    JButton brighten = new JButton("Brighten");
    brighten.setActionCommand("Brighten");
    brighten.addActionListener(this);
    inputDialogPanel.add(brighten);


    //JOptionsPane options dialog
    JPanel optionsDialogPanel = new JPanel();
    optionsDialogPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(optionsDialogPanel);

    // vertical flip
    JButton flipVertical = new JButton("Flip-Vertical");
    flipVertical.setActionCommand("vertical");
    flipVertical.addActionListener(this);
    optionsDialogPanel.add(flipVertical);

    // horizontal flip
    JButton flipHorizontal = new JButton("Flip-Horizontal");
    flipVertical.setActionCommand("horizontal");
    flipVertical.addActionListener(this);
    optionsDialogPanel.add(flipHorizontal);

    // add image flip
    JButton addImage = new JButton("addImage");
    flipVertical.setActionCommand("addImage");
    flipVertical.addActionListener(this);
    optionsDialogPanel.add(addImage);


  }


  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    // TODO Auto-generated method stub
    switch (actionEvent.getActionCommand()) {
      case "Color chooser":
        Color col = JColorChooser.showDialog(JFrameView.this,
                "Choose a color", colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(col);
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Luma":
        model.toGreyScale(Pixel.GreyScaleType.LUMA, "Luma", "Luma");
        break;
      case "brighten":
        model.brighten(1, "Brighten", "Brighten");
        break;
      case "flip-vertical": {
        model.flipVertical("vertical", "vertical");
        break;
      }
      case "flip-horizontal": {
        model.flipHorizontal("horizontal", "horizontal");
        break;
      }
      default:
    }
  }

  /**
   * Displays the provided message to the user.
   *
   * @param m The message that should be rendered.
   */
  @Override
  public void renderMessage(String m) {
    JTextField field = new JTextField();
    field.setText(m);
  }

  /**
   * Writes the model from this view into a PPM file.
   *
   * @param imageName name of the image
   * @param filename name of the file
   */
  @Override
  public void writePPM(String imageName, String filename) {
    this.model.toPPMString(imageName);
  }
}
