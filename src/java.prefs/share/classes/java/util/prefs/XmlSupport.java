package java.util.prefs;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class XmlSupport {

    private static final String PREFS_DTD_URI = "http://java.sun.com/dtd/preferences.dtd";

    private static final String PREFS_DTD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<!-- DTD for preferences -->" + "<!ELEMENT preferences (root) >" + "<!ATTLIST preferences" + " EXTERNAL_XML_VERSION CDATA \"0.0\"  >" + "<!ELEMENT root (map, node*) >" + "<!ATTLIST root" + "          type (system|user) #REQUIRED >" + "<!ELEMENT node (map, node*) >" + "<!ATTLIST node" + "          name CDATA #REQUIRED >" + "<!ELEMENT map (entry*) >" + "<!ATTLIST map" + "  MAP_XML_VERSION CDATA \"0.0\"  >" + "<!ELEMENT entry EMPTY >" + "<!ATTLIST entry" + "          key CDATA #REQUIRED" + "          value CDATA #REQUIRED >";

    private static final String EXTERNAL_XML_VERSION = "1.0";

    private static final String MAP_XML_VERSION = "1.0";

    static void export(OutputStream os, final Preferences p, boolean subTree) throws IOException, BackingStoreException;

    private static void putPreferencesInXml(Element elt, Document doc, Preferences prefs, boolean subTree) throws BackingStoreException;

    static void importPreferences(InputStream is) throws IOException, InvalidPreferencesFormatException;

    private static Document createPrefsDoc(String qname);

    private static Document loadPrefsDoc(InputStream in) throws SAXException, IOException;

    private static final void writeDoc(Document doc, OutputStream out) throws IOException;

    private static void ImportSubtree(Preferences prefsNode, Element xmlNode);

    private static void ImportPrefs(Preferences prefsNode, Element map);

    static void exportMap(OutputStream os, Map<String, String> map) throws IOException;

    static void importMap(InputStream is, Map<String, String> m) throws IOException, InvalidPreferencesFormatException;

    private static class Resolver implements EntityResolver {

        public InputSource resolveEntity(String pid, String sid) throws SAXException;
    }

    private static class EH implements ErrorHandler {

        public void error(SAXParseException x) throws SAXException;

        public void fatalError(SAXParseException x) throws SAXException;

        public void warning(SAXParseException x) throws SAXException;
    }
}
