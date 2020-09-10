package sait.frs.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frs.*;
import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NoMoreSeatsException;
import sait.frs.exception.NullFlightException;
import sait.frs.manager.Manager;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;
/**
 * @author Joel Wood And Zennon Weleschuck
 */

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of travel manager.
	 */
	private Manager manager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	private JLabel flightLabel;
	private JTextField flightSearch;
	private JLabel airlineLable;
	private JTextField airlineSearch;
	private JLabel dayLable;
	private JTextField daySearch;
	private JLabel timeLable;
	private JTextField timeSearch;
	private JLabel costLable;
	private JTextField costSearch;
	private JTextField nameSearch;
	private JLabel nameLable;
	private JLabel citizenshipLable;
	private JTextField citizenshipSearch;
	private JLabel fromLabel;
	private JComboBox fromBox;
	private JLabel toLabel;
	private JComboBox toBox;
	private JLabel dayLabel;
	private JComboBox dayBox;
	private JButton findFlightsButton;
	private JButton reserveButton;

	/**
	 * Creates the components for flights tab.
	 */
	public FlightsTab(Manager manager) {
		this.manager = manager;
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

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);

		JPanel eastPanel = createCenterEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);
		return panel;
	}
	private class MyListSelectionListener implements ListSelectionListener
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			returnText();
		}
	}

	/**
	 * 
	 * @return JPanel that is the east of the greater Center panel
	 */
	private JPanel createCenterEastPanel() {
		// calls CenterEastNorth, CenterEastCenter, CenterEastSouth
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel centerEastNorthPanel = createCenterEastNorth();
		panel.add(centerEastNorthPanel, BorderLayout.NORTH);

		JPanel centerEastCenterPanel = createCenterEastCenter();
		panel.add(centerEastCenterPanel, BorderLayout.CENTER);

		JPanel centerEastSouthPanel = createCenterEastSouth();
		panel.add(centerEastSouthPanel, BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * 
	 * @return JPanel that is the north of the greater Center-East panel
	 */
	private JPanel createCenterEastNorth() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		return panel;
	}

	/**
	 * 
	 * @return JPanel that is the Center of the greater Center-East panel
	 */
	private JPanel createCenterEastCenter() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 8, 1, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		flightLabel = new JLabel("Flight: ");
		flightLabel.setFont(new Font("serif", Font.PLAIN, 12));
		flightLabel.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(flightLabel, gbc);

		flightSearch = new JTextField(25);
		flightSearch.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(flightSearch, gbc);

		airlineLable = new JLabel("Airline: ");
		airlineLable.setFont(new Font("serif", Font.PLAIN, 12));
		airlineLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(airlineLable, gbc);

		airlineSearch = new JTextField(25);
		airlineSearch.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(airlineSearch, gbc);

		dayLable = new JLabel("Day: ");
		dayLable.setFont(new Font("serif", Font.PLAIN, 12));
		dayLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(dayLable, gbc);

		daySearch = new JTextField(25);
		daySearch.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(daySearch, gbc);

		timeLable = new JLabel("Time: ");
		timeLable.setFont(new Font("serif", Font.PLAIN, 12));
		timeLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(timeLable, gbc);

		timeSearch = new JTextField(25);
		timeSearch.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(timeSearch, gbc);

		costLable = new JLabel("Cost: ");
		costLable.setFont(new Font("serif", Font.PLAIN, 12));
		costLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(this.costLable, gbc);

		costSearch = new JTextField(25);
		costSearch.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(costSearch, gbc);

		nameLable = new JLabel("Name: ");
		nameLable.setFont(new Font("serif", Font.PLAIN, 12));
		nameLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(nameLable, gbc);

		nameSearch = new JTextField(25);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(nameSearch, gbc);

		citizenshipLable = new JLabel("Citezenship: ");
		citizenshipLable.setFont(new Font("serif", Font.PLAIN, 12));
		citizenshipLable.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(citizenshipLable, gbc);

		citizenshipSearch = new JTextField(25);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(citizenshipSearch, gbc);

		return panel;
	}

	/**
	 * 
	 * @return JPanel that is the South of the greater Center-East panel
	 */
	private JPanel createCenterEastSouth() {
		JPanel panel = new JPanel();
		reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ButtonListener()); 
		panel.add(reserveButton, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 
	 * @return JPanel that goes in the south
	 */
	private JPanel createSouthPanel() {
		// calls southNorth, southCenter, southSouth
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel southNorthPanel = createSouthNorthPanel();
		panel.add(southNorthPanel, BorderLayout.NORTH);// ,

		JPanel southCenterPanel = createSouthCenterPanel();
		panel.add(southCenterPanel, BorderLayout.CENTER);

		JPanel southSouthPanel = createSouthSouthPanel();
		panel.add(southSouthPanel, BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * 
	 * @return JPanel that is the North panel of the greater South panel
	 */
	private JPanel createSouthNorthPanel() {
		JPanel southNorthPanel = new JPanel();
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		southNorthPanel.add(title);
		return southNorthPanel;
	}

	/**
	 * 
	 * @return JPanel that is the Center panel of the greater South panel
	 */
	private JPanel createSouthCenterPanel() {
		// build airport codes
		String[] airportCodes = new String[this.manager.getAirports().size()];
		manager.getAirports().toArray(airportCodes);
		String[] daysOfWeek = { this.manager.WEEKDAY_ANY, this.manager.WEEKDAY_MONDAY, this.manager.WEEKDAY_TUESDAY,
				this.manager.WEEKDAY_WEDNESDAY, this.manager.WEEKDAY_THURSDAY, this.manager.WEEKDAY_FRIDAY,
				this.manager.WEEKDAY_SATURDAY, this.manager.WEEKDAY_SUNDAY };

		JPanel southCenterPanel = new JPanel();
		southCenterPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;

		fromLabel = new JLabel("From: ");
		fromLabel.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		southCenterPanel.add(fromLabel, gbc);

		fromBox = new JComboBox(airportCodes);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		southCenterPanel.add(fromBox, gbc);

		toLabel = new JLabel("To: ");
		toLabel.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		southCenterPanel.add(toLabel, gbc);

		toBox = new JComboBox(airportCodes);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		southCenterPanel.add(this.toBox, gbc);

		dayLabel = new JLabel("Day: ");
		dayLabel.setHorizontalAlignment(JLabel.RIGHT);
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		southCenterPanel.add(dayLabel, gbc);

		dayBox = new JComboBox(daysOfWeek);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		southCenterPanel.add(dayBox, gbc);

		return southCenterPanel;
	}

	/**
	 * 
	 * @return JPanel that is the South panel of the greater South panel
	 */
	private JPanel createSouthSouthPanel() {
		JPanel SouthSouthPanel = new JPanel();
		SouthSouthPanel.setLayout(new BorderLayout());
		findFlightsButton = new JButton("Find Flights");
		findFlightsButton.addActionListener(new ButtonListener());
		SouthSouthPanel.add(findFlightsButton);
		return SouthSouthPanel;
	}
/**
 * 
 * ButtonListener class is an ActionListener that for the find flights and Reserve tabs
 *
 */
	private class ButtonListener implements ActionListener {
		
		/**
		 * @param ActionEvent when a button is clicked
		 */
		public void actionPerformed(ActionEvent e) {

			// Get the action command.

			if (e.getSource() == findFlightsButton) {
				String from = (String) fromBox.getSelectedItem();
				String to = (String) toBox.getSelectedItem();
				String weekday = (String) dayBox.getSelectedItem();
				flightsModel.clear();

				for (Flight flight : manager.findFlights(from, to, weekday)) {
					flightsModel.addElement(flight);
				}
			}
			if(e.getSource() == reserveButton) {
				Flight flight = flightsList.getSelectedValue();
				String name = nameSearch.getText();
				String citizenship = citizenshipSearch.getText();
				try {
					manager.makeReservation(flight, name, citizenship);
					Reservation r1 = new Reservation(flight, name, citizenship);
					JOptionPane.showMessageDialog(null, "Reservation created your code is " + r1.getCode());
					manager.persist();
				} catch (NullFlightException e1) {
					System.out.println(e1.getMessage());
					System.out.println(e1.getStackTrace());
				}catch (NoMoreSeatsException e2) {
					System.out.println(e2.getMessage());
					System.out.println(e2.getStackTrace());
					
				}catch (InvalidNameException e3) {
					System.out.println(e3.getMessage());
					System.out.println(e3.getStackTrace());
					
				}catch (InvalidCitizenshipException e4) {
					System.out.println(e4.getMessage());
					System.out.println(e4.getStackTrace());
				}
				
			}
		}
	}

	/**
	 * This is the method for displaying information in fields
	 */
	private void returnText() {
		String flightText = "";
		String airlineText = "";
		String dayText = "";
		String timeText = "";
		double costText = 0;
		flightText= flightsList.getSelectedValue().getCode();
		airlineText = flightsList.getSelectedValue().getAirline();
		dayText = flightsList.getSelectedValue().getWeekday();
		timeText = flightsList.getSelectedValue().getTime();
		costText = flightsList.getSelectedValue().getCostPerSeat();
		String cost = Double.toString(costText);
		
		flightSearch.setText(flightText);
		airlineSearch.setText(airlineText);
		daySearch.setText(dayText);
		timeSearch.setText(timeText);
		costSearch.setText(cost);
	}
}
