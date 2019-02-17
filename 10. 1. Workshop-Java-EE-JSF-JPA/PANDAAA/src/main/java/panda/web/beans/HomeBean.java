package panda.web.beans;

import org.modelmapper.ModelMapper;
import panda.domain.models.service.UserServiceModel;
import panda.domain.models.view.PackageViewModel;
import panda.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<PackageViewModel> pendingPackages;
    private List<PackageViewModel> shippedPackages;
    private List<PackageViewModel> deliveredPackages;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initPackages();
    }

    private void initPackages() {
        UserServiceModel userServiceModel = userService
                .findUserByUsername((String) ((HttpSession) FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getSession(true)).getAttribute("username"));

        this.pendingPackages = userServiceModel
                .getPackages()
                .stream()
                .filter(p -> p.getStatus().name().equals("Pending"))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());

        this.shippedPackages = userServiceModel
                .getPackages()
                .stream()
                .filter(p -> p.getStatus().name().equals("Shipped"))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());

        this.deliveredPackages = userServiceModel
                .getPackages()
                .stream()
                .filter(p -> p.getStatus().name().equals("Delivered"))
                .map(p -> this.modelMapper.map(p, PackageViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageViewModel> getPendingPackages() {
        return pendingPackages;
    }

    public void setPendingPackages(List<PackageViewModel> pendingPackages) {
        this.pendingPackages = pendingPackages;
    }

    public List<PackageViewModel> getShippedPackages() {
        return shippedPackages;
    }

    public void setShippedPackages(List<PackageViewModel> shippedPackages) {
        this.shippedPackages = shippedPackages;
    }

    public List<PackageViewModel> getDeliveredPackages() {
        return deliveredPackages;
    }

    public void setDeliveredPackages(List<PackageViewModel> deliveredPackages) {
        this.deliveredPackages = deliveredPackages;
    }
}
