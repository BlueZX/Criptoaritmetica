import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;

public class CrearXml {

    public static ArrayList<String> read(){
        ArrayList<String> res = new ArrayList<String>();
        try {
            File fXmlFile = new File("puzzle.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("operacion");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    res.add("" + eElement.getElementsByTagName("operando").item(0).getTextContent()+ " "+eElement.getElementsByTagName("operador").item(0).getTextContent() + " "+eElement.getElementsByTagName("operando").item(1).getTextContent() +" = "+ eElement.getElementsByTagName("resultado").item(0).getTextContent()+" ");
                }
            }

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void generar(ArrayList<String> o1,ArrayList<String> op,ArrayList<String> o2,ArrayList<String> r) throws Exception{

        if(o1.isEmpty() || op.isEmpty() || o1.size()!=op.size()){
            System.out.println("ERROR empty ArrayList");
            return;
        }
        else{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "puzzle", null);

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada operadorando creamos un item que contendrá la o1, op y el o2
            for(int i=0; i<o1.size();i++){
                //operacion Node
                Element itemNode = document.createElement("operacion"); 
                //o1 Node
                Element o1Node = document.createElement("operando"); 
                Text nodeO1Value = document.createTextNode(o1.get(i));
                o1Node.appendChild(nodeO1Value);      
                //op Node
                Element opNode = document.createElement("operador"); 
                Text nodeOpValue = document.createTextNode(op.get(i));                
                opNode.appendChild(nodeOpValue);
                //o2 Node
                Element o2Node = document.createElement("operando"); 
                Text nodeO2Value = document.createTextNode(o2.get(i));
                o2Node.appendChild(nodeO2Value); 
                //op Node
                Element rNode = document.createElement("resultado"); 
                Text nodeRValue = document.createTextNode(r.get(i));                
                rNode.appendChild(nodeRValue);
                
                itemNode.appendChild(o1Node);
                itemNode.appendChild(opNode);
                itemNode.appendChild(o2Node);
                itemNode.appendChild(rNode);
                
                //pegamos el elemento a la raiz "Documento"
                raiz.appendChild(itemNode); 
            }                
            //Generamos el XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File("solucion.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }

}