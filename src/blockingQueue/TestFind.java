package blockingQueue;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*public class TestFind extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	private File selectedFile;
	private File selectedImg;
	private String caminho = "/Users/ivocosta/Downloads/EssaPorraJaTaEnchendoOSaco/img/mod/";
	private File f = new File("/Users/ivocosta/git/Projeto-PCD/img/modificadas");
	private int angulo = 0;

	public TestFind() {
		initComponents();
	}

	public void addCaminhoImg() {
		selectedImg = TesteFindImage.fileChooser();
		jTextField2.setText(selectedImg.toString() + "/Superman.png");
	}

	public void addCaminho() {
		selectedFile = TesteFindImage.fileChooser();
		jTextField1.setText(selectedFile.toString());
	}
	public void addBlockingQueue(File selectedFile, File selectedImg,String caminho,int angulo) {
		Tarefa add= new Tarefa( selectedFile, selectedImg, caminho, angulo);
		
	}

	public void addListImage() throws IOException {
			String img = "";
			for (File file : selectedFile.listFiles()) {
				img = selectedFile + "/" + file.getName();
				System.out.println(img);
				System.out.println(selectedImg.toString());
				BufferedImage find = ImageIO.read(new File(selectedImg.toString() + "/Superman.png"));
				BufferedImage image = ImageIO.read(new File(img));
				String temp = caminho;
				caminho += file.getName();
				if(!file.getName().equals(".DS_Store")) {
					TesteFindImage.Vixi(find, image, caminho,0);
					caminho = temp;
				
				}else {
					System.out.println(file.toString());
				}
					
				
			}

			//for (File a : f.listFiles()) {
			//	dlm.addElement(a.getName());
			//}

			listaDeImagem.setModel(dlm);
			listaDePesquisa.setModel(dlm);

		}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		listaDeImagem = new javax.swing.JList<>();
		btnProcura = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		btnPasta = new javax.swing.JButton();
		btnImage = new javax.swing.JButton();
		painelImagem = new javax.swing.JPanel();
		show_image = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		listaDePesquisa = new javax.swing.JList<>();

		jScrollPane1.setViewportView(listaDeImagem);

		btnProcura.setText("Procurar");
		btnProcura.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnProcuraActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		btnPasta.setText("Pasta");
		btnPasta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPastaActionPerformed(evt);
			}
		});

		btnImage.setText("Imgem");
		btnImage.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnImageActionPerformed(evt);
			}
		});

		painelImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		show_image.addAncestorListener(new javax.swing.event.AncestorListener() {
			public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
			}

			public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
				show_imageAncestorAdded(evt);
			}

			public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
			}
		});

		javax.swing.GroupLayout painelImagemLayout = new javax.swing.GroupLayout(painelImagem);
		painelImagem.setLayout(painelImagemLayout);
		painelImagemLayout
				.setHorizontalGroup(painelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								painelImagemLayout.createSequentialGroup().addGap(0, 30, Short.MAX_VALUE).addComponent(
										show_image, javax.swing.GroupLayout.PREFERRED_SIZE, 612,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		painelImagemLayout.setVerticalGroup(
				painelImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
						show_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		jScrollPane3.setViewportView(listaDePesquisa);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(btnProcura, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 795,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 795,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
														Short.MAX_VALUE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(painelImagem, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(btnPasta, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnImage, javax.swing.GroupLayout.DEFAULT_SIZE, 144,
												Short.MAX_VALUE)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
												Short.MAX_VALUE))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane1)
								.addComponent(painelImagem, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPasta))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnImage))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnProcura)
						.addContainerGap()));

		pack();
	}

	private void btnProcuraActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		addListImage();
	}

	private void show_imageAncestorAdded(javax.swing.event.AncestorEvent evt) {
		listaDeImagem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedValue = listaDeImagem.getSelectedValue();
					ImageIcon m = new ImageIcon(f.toString() + "/" + selectedValue);
					show_image.setIcon(m);
				}

			}
		});

	}

	private void btnPastaActionPerformed(java.awt.event.ActionEvent evt) {
		addCaminho();
	}

	private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {
		addCaminhoImg();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TestFind().setVisible(true);

			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnProcura;
	private javax.swing.JButton btnPasta;
	private javax.swing.JButton btnImage;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JList<String> listaDeImagem;
	private javax.swing.JList<String> listaDePesquisa;
	private javax.swing.JPanel painelImagem;
	private javax.swing.JLabel show_image;
	// End of variables declaration
}
*/