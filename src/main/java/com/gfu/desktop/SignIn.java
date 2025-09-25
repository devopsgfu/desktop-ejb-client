package com.gfu.desktop;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import address.AddressInterface;
import login.LoginInterface;

public class SignIn {


    static {
        // Disable GlassFish SSL before any initialization
        System.setProperty("com.sun.enterprise.iiop.ssl.disable", "true");
        System.setProperty("com.sun.enterprise.security.httpsOutboundKeyAlias", "");
        System.setProperty("javax.net.ssl.keyStore", "");
        System.setProperty("javax.net.ssl.trustStore", "");
    }



    private AddressInterface addressInterface;
    private LoginInterface loginInterface;





    public SignIn() {


        // JNDI Name für Ihr EJB - Format:
        // java:global/[EAR-Name]/[EJB-JAR-Name]/[Bean-Class-Name]![Interface-Name]
        final String jndiNameAdress = "java:global/ear-module-1.0.0/ejb-module/Address!address.AddressInterface";
        final String jndiNameLogin = "java:global/ear-module-1.0.0/ejb-module/Login!login.LoginInterface";
        addressInterface = lookupEJB(jndiNameAdress, AddressInterface.class);
    }



    public static InitialContext createContext() throws NamingException {
        Properties props = new Properties();

        // Use standard CORBA naming - no GlassFish specifics
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.cosnaming.CNCtxFactory");
        props.put(Context.PROVIDER_URL, "iiop://localhost:3700");

        return new InitialContext(props);
    }

    public static <T> T lookupEJB(String jndiName, Class<T> interfaceClass) {
        try {
            InitialContext ctx = createContext();
            Object obj = ctx.lookup(jndiName);
            return interfaceClass.cast(obj);
        } catch (NamingException e) {
            throw new RuntimeException("EJB lookup failed for: " + jndiName, e);
        }
    }


    public  AddressInterface getAddressInterface() {
        return addressInterface;
    }

    public  LoginInterface getLoginInterface() {
        return loginInterface;
    }

}
