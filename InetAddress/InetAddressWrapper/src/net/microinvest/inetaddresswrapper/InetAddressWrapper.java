package net.microinvest.inetaddresswrapper;

import java.net.InetAddress;

import anywheresoftware.b4a.BA;

@BA.Version(0.01F)
@BA.ShortName("InetAddress")
@BA.Permissions(values = {"android.permission.INTERNET"})
public class InetAddressWrapper {
    public String GetHostByName(String hostName) {
        try {
            InetAddress inetAddress = InetAddress.getByName(hostName);
            return inetAddress.getHostAddress();
        }catch(Exception e) {
            return "";
        }
    }
}
