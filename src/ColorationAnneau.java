import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

/**
 *
 * @author Charbel
 */
public class ColorationAnneau extends LC1_Algorithm{
	String options[] = {"A","F","E"};
    @Override
    public String getDescription() {
        return "Couleur Anneau";
        
    }

    @Override
    protected void beforeStart() {
    	
        this.setLocalProperty("label", vertex.getLabel());

       
        
    }

    @Override
    protected void onStarCenter() {
    	Object neighborColors[] = {this.getNeighborProperty(0, "label"),this.getNeighborProperty(1, "label")};
        
        int count = 0;
        for(int i=0;i<2;i++) {
        	if(neighborColors[i].equals(this.getLocalProperty("label"))) {
        		
        		count++;
        	}
        	
        }
        if(count==2) {
        	int i=0;
        	for(;i<3;i++) {
        		if(!options[i].equals(this.getLocalProperty("label"))) {
        			break;
        		}
        	}
        	this.setLocalProperty("label", options[i]);
        }
        else if(count == 1) {
        	int i = 0;
        	for(;i<3;i++) {
        		if(!options[i].equals(neighborColors[0]) && !options[i].equals(neighborColors[1])) {
        			break;
        		}
        	}
        	this.setLocalProperty("label", options[i]);
        }
        
        
    }


    @Override
    public Object clone() {
        return new ColorationAnneau();
    }
    
   
    
   
    
}
