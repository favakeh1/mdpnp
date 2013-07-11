/*******************************************************************************
 * Copyright (c) 2012 MD PnP Program.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package org.mdpnp.guis.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.rti.dds.subscription.SampleInfo;

@SuppressWarnings("serial")
public abstract class DevicePanel extends JComponent {
    
	public DevicePanel() {
		setOpaque(false);
	}
	
	private static void setBackground(Container c, Color bg) {
		for(Component comp : c.getComponents()) {
			comp.setBackground(bg);
			if(comp instanceof Container) {
				setBackground((Container) comp, bg);
			}
		}
	}
	
	private static void setForeground(Container c, Color fg) {
		for(Component comp : c.getComponents()) {
			comp.setForeground(fg);
			if(comp instanceof Container) {
				setForeground((Container) comp, fg);
			}
		}
	}
	
	protected void setInt(Integer i, JLabel label, String alt) {
		label.setText(null == i ? alt : Integer.toString(i));
	}
	protected void setInt(Number n, JLabel label, String alt) {
		setInt(null == n ? null : n.intValue(), label, alt);
	}
	
   protected final void setInt(ice.Numeric sample, int name, JLabel label, String def) {
        if(sample.name == name) {
            setInt(sample.value, label, def);
            if(!label.isVisible()) {
                label.setVisible(true);
            }
        }
    }
	
	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		setForeground(this, fg);
	}
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		setBackground(this, bg);
	}
	
//	public abstract deviceIdentity(DeviceIdentity di);
//	public abstract deviceConnectivity(DeviceConnectivity dc);
	public abstract void numeric(ice.Numeric numeric, SampleInfo sampleInfo);
	public abstract void sampleArray(ice.SampleArray sampleArray, SampleInfo sampleInfo);
	
	public void destroy() {
	}
	
}
