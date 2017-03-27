package ca.ubc.cs.cpsc210.resourcefinder.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Represents the state of user's selection of services
public class SelectionState {
    private Set<Service> selected;
    private ResourceRegistry registry;
    private boolean isAnySelected;     // true for 'any', false for 'all'

    // EFFECTS: constructs selection state with empty set of selected services and 'any' service selected
    public SelectionState(ResourceRegistry registry) {
        this.registry = registry;
        selected = new HashSet<>();
        isAnySelected = true;
    }

    // MODIFIES: this
    // EFFECTS: select resources with 'any' of the selected services
    public void setSelectAny() {
        isAnySelected = true;
    }

    // MODIFIES: this
    // EFFECTS: select resources with 'all' of the selected servicesX
    public void setSelectAll() {
        isAnySelected = false;
    }

    // MODIFIES: this
    // EFFECTS: add service to selected services
    public void selectService(Service service) {
        selected.add(service);
    }

    // MODIFIES: this
    // EFFECTS: remove service from selected services
    public void deselectService(Service service) {
        selected.remove(service);
    }

    // MODIFIES: this
    // EFFECTS: set selected services to given set of services
    public void setSelectedServices(Set<Service> selected) {
        this.selected = selected;
    }

    // EFFECTS: return set of resources corresponding to current selection;
    // if no services are currently selected, set of all resources is returned, otherwise:
    //    - if 'any' requested, return resources offering any of the selected services
    //    - if 'all' requested, return resources offering all of the selected services
    public Set<Resource> getResourcesWithSelectedServices() {
        Set<Resource> resourcesset;
        resourcesset = new HashSet<>();
        if (selected.isEmpty()) {
            for (Resource item : registry.getResources()) {
                resourcesset.add(item);
            }
        }
        else
            if (isAnySelected) {
            resourcesset = registry.getResourcesOfferingAnyServicesInSet(selected);
        }
        else {
            resourcesset = registry.getResourcesOfferingAllServicesInSet(selected);
        }
        return resourcesset;
    }
}
