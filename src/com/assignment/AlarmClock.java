package com.assignment;

/*Question 2:
Please implement an alarm clock using Object Oriented Programming. If you are not familiar or comfortable with 
object-oriented programming, we suggest you give this article a quick read to ensure you understand it before you 
attempt it.
The alarm clock should have the following features:
• It displays the current time
• A user can create any number of alarms by specifying the alarm time and day of the week and time when the 
alarm should alert
• A user can snooze an alarm maximum of 3 times at an interval of 5 minutes each.
• A user can delete an alarm
*/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AlarmClock {

	private JFrame frame;
	private JLabel lbl_CD, lbl_CT, lbl_AT;
	private static JTextField txt_hrs, txt_mins, txt_secs, txt_ampm;
	private JButton btn_SA, btn_ST;

	public int temp_h, temp_m, temp_s;
	public String temp_am, am_pm;
	private int flag;
	private Thread clock;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlarmClock window = new AlarmClock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlarmClock() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Alarm Clock");
		frame.setBounds(100, 100, 352, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lbl1 = new JLabel("Current Date    :");
		lbl1.setBounds(25, 23, 117, 14);
		frame.getContentPane().add(lbl1);

		JLabel lbl2 = new JLabel("Current Time    :");
		lbl2.setBounds(25, 52, 117, 17);
		frame.getContentPane().add(lbl2);

		JLabel lbl3 = new JLabel("Alarm Time       :");
		lbl3.setBounds(25, 80, 117, 17);
		frame.getContentPane().add(lbl3);

		lbl_CD = new JLabel("-");
		lbl_CD.setBounds(146, 20, 180, 25);
		frame.getContentPane().add(lbl_CD);

		lbl_CT = new JLabel("-");
		lbl_CT.setBounds(146, 48, 166, 25);
		frame.getContentPane().add(lbl_CT);

		lbl_AT = new JLabel("00:00:00 --");
		lbl_AT.setBounds(146, 76, 166, 25);
		frame.getContentPane().add(lbl_AT);

		JSeparator separator = new JSeparator();
		separator.setBounds(25, 108, 287, 2);
		frame.getContentPane().add(separator);

		JLabel lbl4 = new JLabel("Enter Time        :");
		lbl4.setBounds(25, 131, 117, 14);
		frame.getContentPane().add(lbl4);

		txt_hrs = new JTextField();
		txt_hrs.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((((e.getKeyChar() >= 48) && (e.getKeyChar() <= 57))
						|| ((e.getKeyChar() == 8) || (e.getKeyCode() == KeyEvent.VK_ENTER)
								|| (e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)))) {
					String hours = txt_hrs.getText().trim();
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (hours.equals("")) {
							JOptionPane.showMessageDialog(frame, "fill-up hour/s first!", "Message",
									JOptionPane.ERROR_MESSAGE);
							txt_hrs.requestFocus();
						} else {
							txt_mins.requestFocus();
						}
					}
				} else {
					e.setKeyCode(KeyEvent.VK_BACK_SPACE);
					JOptionPane.showMessageDialog(frame, "Please enter valid number", "Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txt_hrs.setColumns(10);
		txt_hrs.setBounds(146, 125, 30, 20);
		frame.getContentPane().add(txt_hrs);

		txt_mins = new JTextField();
		txt_mins.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((((e.getKeyChar() >= 48) && (e.getKeyChar() <= 57))
						|| ((e.getKeyChar() == 8) || (e.getKeyCode() == KeyEvent.VK_ENTER)
								|| (e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)))) {
					String minutes = txt_mins.getText().trim();
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (minutes.equals("")) {
							JOptionPane.showMessageDialog(frame, "fill-up minute/s first!", "Message",
									JOptionPane.ERROR_MESSAGE);
							txt_mins.requestFocus();
						} else {
							txt_secs.requestFocus();
						}
					}
				} else {
					e.setKeyCode(KeyEvent.VK_BACK_SPACE);
					JOptionPane.showMessageDialog(frame, "Please enter valid number", "Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txt_mins.setColumns(10);
		txt_mins.setBounds(178, 125, 30, 20);
		frame.getContentPane().add(txt_mins);

		txt_secs = new JTextField();
		txt_secs.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((((e.getKeyChar() >= 48) && (e.getKeyChar() <= 57))
						|| ((e.getKeyChar() == 8) || (e.getKeyCode() == KeyEvent.VK_ENTER)
								|| (e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)))) {
					String seconds = txt_secs.getText().trim();
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						if (seconds.equals("")) {
							JOptionPane.showMessageDialog(frame, "fill-up second/s first!", "Message",
									JOptionPane.ERROR_MESSAGE);
							txt_secs.requestFocus();
						} else {
							txt_ampm.requestFocus();
						}
					}
				} else {
					e.setKeyCode(KeyEvent.VK_BACK_SPACE);
					JOptionPane.showMessageDialog(frame, "Please enter valid number", "Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txt_secs.setColumns(10);
		txt_secs.setBounds(210, 125, 30, 20);
		frame.getContentPane().add(txt_secs);

		txt_ampm = new JTextField();
		txt_ampm.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String ampm = txt_ampm.getText().toUpperCase().trim();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!(ampm.equals("AM")) && !(ampm.equals("PM")) || ampm.equals("")) {
						JOptionPane.showMessageDialog(frame, "Please enter AM or PM first!", ampm,
								JOptionPane.ERROR_MESSAGE);
						txt_ampm.setText(null);
						txt_ampm.requestFocus();
					} else {
						btn_ST.requestFocus();
					}
				}
			}
		});
		txt_ampm.setColumns(10);
		txt_ampm.setBounds(242, 125, 30, 20);
		frame.getContentPane().add(txt_ampm);

		JLabel lblHrs = new JLabel("Hr");
		lblHrs.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrs.setBounds(146, 146, 30, 17);
		frame.getContentPane().add(lblHrs);

		JLabel lblMins = new JLabel("Min");
		lblMins.setHorizontalAlignment(SwingConstants.CENTER);
		lblMins.setBounds(178, 146, 30, 17);
		frame.getContentPane().add(lblMins);

		JLabel lblSecs = new JLabel("Sec");
		lblSecs.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecs.setBounds(210, 146, 30, 17);
		frame.getContentPane().add(lblSecs);

		btn_ST = new JButton(" SET TIME");
		btn_ST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTime();
			}
		});
		btn_ST.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setTime();
				}
			}
		});
		btn_ST.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btn_ST.setBounds(25, 174, 143, 25);
		frame.getContentPane().add(btn_ST);

		btn_SA = new JButton("STOP ALARM");
		btn_SA.setEnabled(false);
		btn_SA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
			}
		});
		btn_SA.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					flag = 2;
				}
			}
		});
		btn_SA.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btn_SA.setBounds(169, 174, 143, 25);
		frame.getContentPane().add(btn_SA);

		clock = new Thread() {
			public void run() {
				try {
					while (true) {
						Date date = new Date();// get current date
						SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");

						Calendar datetime = Calendar.getInstance();// get week day
						String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
								"Friday", "Saturday" };
						String wd = strDays[datetime.get(Calendar.DAY_OF_WEEK) - 1];

						Calendar cal = new GregorianCalendar();
						int hour = cal.get(Calendar.HOUR);
						int minute = cal.get(Calendar.MINUTE);
						int second = cal.get(Calendar.SECOND);

						if (datetime.get(Calendar.AM_PM) == Calendar.AM) {
							am_pm = "AM";
						} else if (datetime.get(Calendar.AM_PM) == Calendar.PM) {
							am_pm = "PM";
						}

						if (temp_h == hour && temp_m == minute && temp_s == second && temp_am.equals(am_pm)
								&& flag == 1) {
							txt_hrs.setText("");
							txt_mins.setText("");
							txt_secs.setText("");
							txt_ampm.setText("");
							JOptionPane.showMessageDialog(null, "Stop Alarm ");
						} else if (flag == 2) {
							lbl_AT.setText("00:00:00 --");
							txt_hrs.setEnabled(true);
							txt_mins.setEnabled(true);
							txt_secs.setEnabled(true);
							txt_ampm.setEnabled(true);
							btn_ST.setEnabled(true);
							btn_SA.setEnabled(false);
						}

						lbl_CD.setText(sf.format(date) + " - " + wd);
						lbl_CT.setText("" + hour + ":" + minute + ":" + second + " " + am_pm + "");

						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

	public void setTime() {
		String hours = txt_hrs.getText().trim();
		String minutes = txt_mins.getText().trim();
		String seconds = txt_secs.getText().trim();
		String ampm = txt_ampm.getText().toUpperCase().trim();

		if (hours.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up hour/s first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt_hrs.requestFocus();
		} else if (minutes.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up minute/s first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt_mins.requestFocus();
		} else if (seconds.equals("")) {
			JOptionPane.showMessageDialog(frame, "fill-up second/s first!", "Message", JOptionPane.ERROR_MESSAGE);
			txt_secs.requestFocus();
		} else if (!(ampm.equals("AM")) && !(ampm.equals("PM")) || ampm.equals("")) {
			JOptionPane.showMessageDialog(frame, "Please enter AM or PM first!", ampm, JOptionPane.ERROR_MESSAGE);
			txt_ampm.setText(null);
			txt_ampm.requestFocus();
		} else {
			temp_h = Integer.parseInt(txt_hrs.getText().trim());
			temp_m = Integer.parseInt(txt_mins.getText().trim());
			temp_s = Integer.parseInt(txt_secs.getText().trim());
			temp_am = txt_ampm.getText().toUpperCase().trim();
			flag = 1;
			lbl_AT.setText("" + temp_h + ":" + temp_m + ":" + temp_s + " " + temp_am + "");
			btn_ST.setEnabled(false);
			btn_SA.setEnabled(true);
			txt_hrs.setEnabled(false);
			txt_mins.setEnabled(false);
			txt_secs.setEnabled(false);
			txt_ampm.setEnabled(false);
		}
	}
}