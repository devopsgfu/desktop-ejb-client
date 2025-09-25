package com.gfu.desktop;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import address.AddressInterface;
import login.LoginInterface;

public class SignIn {


    private AddressInterface addressInterface;
    private LoginInterface loginInterface;
    private void lookupEJB() throws NamingException {
        // JNDI Properties für GlassFish
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty(Context.URL_PKG_PREFIXES,
                "com.sun.enterprise.naming");
        props.setProperty(Context.PROVIDER_URL,
                "iiop://localhost:3700");  // GlassFish IIOP Port

        // InitialContext erstellen
        Context context = new InitialContext(props);

        // JNDI Name für Ihr EJB - Format:
        // java:global/[EAR-Name]/[EJB-JAR-Name]/[Bean-Class-Name]![Interface-Name]
        String jndiName = "java:global/ear-module-1.0.0/ejb-module/UserServiceBean!com.example.interfaces.UserService";

        // Remote EJB lookup
        addressInterface = (AddressInterface) context.lookup(jndiName);
        loginInterface = (LoginInterface) context.lookup(jndiName);

        System.out.println("✅ JNDI Lookup erfolgreich!");
    }






}
