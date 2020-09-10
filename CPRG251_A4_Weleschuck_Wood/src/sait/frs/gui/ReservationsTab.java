package sait.frs.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.naming.InvalidNameException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frs.exception.InvalidCitizenshipException;
//import sait.frs.exception.InvalidCitizenshipException;
//import sait.frs.exception.InvalidNameException;
//import sait.frs.exception.NoMoreSeatsException;
//import sait.frs.exception.NullFlightException;
//import sait.frs.manager.Manager;
import sait.frs.manager.ReservationManager;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

/**
 * @author Zennon and Joel
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * field for ReservationsTab
	 */
	private JTextArea reserveTextArea;
	private JTextField codeReserveText;
	private JTextField flightReserveText;
	private JTextField AirlineReserveText;
	private JTextField costReserveText;
	private JTextField nameReserveText;
	private JTextField citizenReserveText;
	private JComboBox statusReserveComboBox;
	private JButton updateButton;

	private JTextField codeSearchText;
	private JTextField airlineSearchText;
	private JTextField nameSearchText;
	private JButton findResButton;

	private DefaultListModel<Reservation> reservationsModel;
	private JList<Reservation> reservationList;
	/**
	 * Instance of travel manager.
	 */
	private ReservationManager ReservationManager;
	private Reservation reservation;

	/**
	 * Creates the components for reservations tab.
	 * @param manager
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.ReservationManager = reservationManager;
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}
	/**
	 * creates center panel
	 * @return JPanel that goes in center
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel westPanel = createWestPanel();
		panel.add(westPanel, BorderLayout.WEST);

		return panel;
	}
	/**
	 * creates cente west panel
	 * @return JPanel that goes in the center west
	 */
	private JPanel createWestPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(8, 8));
		reservationsModel = new DefaultListModel<>();
		reservationList = new JList<>(reservationsModel);

		JScrollPane reserveTextArea = new JScrollPane(this.reservationList);

		reservationList.addListSelectionListener(new MyListSelectionListerner());
		panel.add(reserveTextArea);
		panel.add(new JScrollPane(reserveTextArea));
		return panel;
	}
	/**
	 * listener for loading infomation to be displayed in fields
	 * @author Zennon and Joel
	 *
	 */
	public class MyListSelectionListerner implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			returnText();

		}
		
		/**
		 * method for displaying information in fields
		 */
		private void returnText() {
			String codeText = "";
			String flightText = "";
			String airlineText = "";
			String costText = "";
			String nameText = "";
			String citizenText = "";
			
			
			
			codeText = reservationList.getSelectedValue().getCode();
			flightText = reservationList.getSelectedValue().getFlightCode();
			airlineText = reservationList.getSelectedValue().getAirline();
			costText = Double.toString(reservationList.getSelectedValue().getCost());
			nameText = reservationList.getSelectedValue().getName();
			citizenText = reservationList.getSelectedValue().getCitizenship();
			boolean activeStatus = reservationList.getSelectedValue().isActive();
			
			codeReserveText.setText(codeText);
			flightReserveText.setText(flightText);
			AirlineReserveText.setText(airlineText);
			costReserveText.setText(costText);
			nameReserveText.setText(nameText);
			citizenReserveText.setText(citizenText);
			
			if (activeStatus == true) {
				statusReserveComboBox.setSelectedItem("active");
			}else {
				statusReserveComboBox.setSelectedItem("inactive");
			}
			
		}

	}
	/**
	 * creates JPanel for the center east panel
	 * @return JPanel for center east section
	 */
	private JPanel createEastPanel() {// main panel for east portion
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel centerNorthPanel = createEastNorthPanel();
		panel.add(centerNorthPanel, BorderLayout.NORTH);

		JPanel centerCenterPanel = createEastCenterPanel();
		panel.add(centerCenterPanel, BorderLayout.CENTER);

		JPanel eastSouthPanel = createEastSouthPanel();
		panel.add(eastSouthPanel, BorderLayout.SOUTH);

		return panel;
	}
	/**
	 * creates the north section within the east center panel
	 * @return JPanel for the north portion of the east section
	 */
	private JPanel createEastNorthPanel() {// main panel for east portion
		JPanel panel = new JPanel();
		JLabel reserveLabel = new JLabel("Reserve", SwingConstants.CENTER);
		reserveLabel.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(reserveLabel);
		return panel;
	}
	/**
	 * creates the central section for the east panel 
	 * @return JPanel for central section of the east panel
	 */
	private JPanel createEastCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 8, 1, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel codeReserveLabel = new JLabel("Code:");
		codeReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(codeReserveLabel, gbc);

		codeReserveText = new JTextField(25);
		codeReserveText.setHorizontalAlignment(JLabel.RIGHT);
		codeReserveText.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(codeReserveText, gbc);

		JLabel flightReserveLabel = new JLabel("Flight:");
		flightReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(flightReserveLabel, gbc);

		flightReserveText = new JTextField(25);
		flightReserveText.setHorizontalAlignment(JLabel.RIGHT);
		flightReserveText.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(flightReserveText, gbc);

		JLabel AirlineReserveLabel = new JLabel("Airline:");
		AirlineReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(AirlineReserveLabel, gbc);

		AirlineReserveText = new JTextField(25);
		AirlineReserveText.setHorizontalAlignment(JLabel.RIGHT);
		AirlineReserveText.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(AirlineReserveText, gbc);

		JLabel costReserveLabel = new JLabel("Cost:");
		costReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(costReserveLabel, gbc);

		costReserveText = new JTextField(25);
		costReserveText.setHorizontalAlignment(JLabel.RIGHT);
		costReserveText.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(costReserveText, gbc);

		JLabel nameReserveLabel = new JLabel("Name:");
		nameReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(nameReserveLabel, gbc);

		nameReserveText = new JTextField(25);
		nameReserveText.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(nameReserveText, gbc);

		JLabel citizenReserveLabel = new JLabel("Citizenship:");
		citizenReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(citizenReserveLabel, gbc);

		citizenReserveText = new JTextField(25);
		citizenReserveText.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(citizenReserveText, gbc);

		JLabel statusReserveLabel = new JLabel("Status:");
		statusReserveLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(statusReserveLabel, gbc);

		String[] statusArray = { "active", "inactive" };// boolean: active==true, inactive==false
		statusReserveComboBox = new JComboBox(statusArray);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(statusReserveComboBox, gbc);

		return panel;

	}
	/**
	 * creates  the south section for the east panel
	 * @return JPanel for the south section of the east panel
	 */
	private JPanel createEastSouthPanel() {
		JPanel panel = new JPanel();
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ButtonListener());
		panel.add(updateButton, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * creates the south panel 
	 * @return JPanel for the south section 
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel southNorthPanel = createSouthNorthPanel();
		panel.add(southNorthPanel, BorderLayout.NORTH);

		JPanel southCentralPanel = createSouthCenterPanel();
		panel.add(southCentralPanel, BorderLayout.CENTER);

		JPanel southSouthPanel = createSouthSouthPanel();
		panel.add(southSouthPanel, BorderLayout.SOUTH);
		return panel;
	}
	/**
	 * creates the north section of the south panel 
	 * @return  JPanel for the north section of the south panel
	 */
	private JPanel createSouthNorthPanel() {
		JPanel panel = new JPanel();
		JLabel searchLabel = new JLabel("Search", SwingConstants.CENTER);
		searchLabel.setFont(new Font("serif", Font.PLAIN, 25));
		panel.add(searchLabel);
		return panel;
	}
	/**
	 * creates the south section for the  south panel
	 * @return JPanel for the south section of the south panel
	 */
	private JPanel createSouthSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		findResButton = new JButton("Find Reservations");
		findResButton.addActionListener(new ButtonListener());
		panel.add(findResButton);
		return panel;
	}
	/**
	 * creates the central section for the south panel
	 * @return JPanel for the central section of the south panel
	 */
	private JPanel createSouthCenterPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 8, 1, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel codeSearchLabel = new JLabel("Code:");
		codeSearchLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridy = 0;
		panel.add(codeSearchLabel, gbc);

		JLabel airlineSearchLabel = new JLabel("Airline:");
		airlineSearchLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy = 1;
		panel.add(airlineSearchLabel, gbc);

		JLabel NameSearchLabel = new JLabel("Name:");
		NameSearchLabel.setFont(new Font("serif", Font.PLAIN, 12));
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.gridy = 2;
		panel.add(NameSearchLabel, gbc);

		codeSearchText = new JTextField(50);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(codeSearchText, gbc);

		airlineSearchText = new JTextField(50);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(airlineSearchText, gbc);

		nameSearchText = new JTextField(50);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(nameSearchText, gbc);

		return panel;
	}
	/**
	 * Listener for the buttons for the reservation tab
	 * @author Zennon and Joel
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// codeSearchText,airlineSearchText,nameSearchText
			// reserveTextArea

			if (e.getSource() == findResButton) {
				ReservationManager.populateFromBinary();
				reservationsModel.clear();
				ReservationManager.findReservations(codeSearchText.getText(),airlineSearchText.getText(),nameSearchText.getText());
				for (Reservation r1 : ReservationManager.findReservations(codeSearchText.getText(),airlineSearchText.getText(),nameSearchText.getText())){
					reservationsModel.addElement(r1);
						 }
				}
				
				/**for (Reservation reservation : ReservationManager.findReservations
				 * (codeSearchText.getText(),
						airlineSearchText.getText(), nameSearchText.getText())) {
					reservationsModel.addElement(reservation);
				}*/
			


			
			if (e.getSource() == updateButton) {
				Reservation r1 = reservationList.getSelectedValue();
				//reservation.setName(nameReserveText.getText());
				//reservation.setCitizenship(citizenReserveText.getText());

				r1.setName(nameReserveText.getText());
				r1.setCitizenship(citizenReserveText.getText());
//>>>>>>> branch 'master' of https://github.com/joeljwood/CPRG251_A4_Weleschuck_Wood.git
				String status = (String) statusReserveComboBox.getSelectedItem();
				if (status.equals("active")) {
					r1.setActive(true);
					JOptionPane.showMessageDialog(null, "Reservation updated, name: " 
							+ r1.getName() + "   Citizenship: " + r1.getCitizenship() + "   Status: active");
				}
				if (status.equals("inactive")) {
					r1.setActive(false);
					JOptionPane.showMessageDialog(null, "Reservation updated, name: " 
							+ r1.getName() + "   Citizenship: " + r1.getCitizenship() + "   Status: inactive");
				}
	
					try {
						ReservationManager.persist();
					} catch (IOException e1) {
						System.out.println("persist in update button had an error");
						e1.printStackTrace();
					}
				/*finally {
					reservation.setActive(false);
					JOptionPane.showMessageDialog(null, "Reservation updated, name: " 
							+ reservation.getName() + "   Citizenship: " + reservation.getCitizenship() + "   Status: inactive");
					}*/
				try {
					ReservationManager.persist();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
		

	}	
}
}
}

