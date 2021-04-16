import java.util.ArrayList;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

/**
 *
 * @author Charbel
 */
public class ColorationGrille extends LC1_Algorithm{
	String options[] = {"A","N","E"};
    @Override
    public String getDescription() {
        return "Couleur Grille";
        
    }

    @Override
    protected void beforeStart() {
    	
        this.setLocalProperty("label", vertex.getLabel());

       
        
    }

    @Override
    protected void onStarCenter() {
    	ArrayList<Object> neighborColors = new ArrayList<>();
    	for(int i = 0; i < getActiveDoors().size(); i++) {
    		int numPort = getActiveDoors().get(i);
    		neighborColors.add(this.getNeighborProperty(numPort, "label"));
    	}
        
        
        if(neighborColors.contains(this.getLocalProperty("label"))) {
        	int i=0;
        	for(;i<options.length;i++) {
        		if(!neighborColors.contains(options[i])) {
        			break;
        		}
        	}
        	if(i<options.length) {
        		this.setLocalProperty("label", options[i]);
        	}
        	
        }
    	
        
        
        
    }


    @Override
    public Object clone() {
        return new ColorationGrille();
    }
    
   
    
   
    
}
