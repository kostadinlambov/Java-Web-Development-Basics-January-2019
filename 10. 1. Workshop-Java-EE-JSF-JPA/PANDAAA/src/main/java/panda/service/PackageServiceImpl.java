package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.service.PackageServiceModel;
import panda.repository.PackageRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void packageCreate(PackageServiceModel packageServiceModel) {
        Package aPackage = this.modelMapper.map(packageServiceModel, Package.class);
        aPackage.setStatus(Status.Pending);

        this.packageRepository.save(aPackage);
    }

    @Override
    public List<PackageServiceModel> findAllPackagesByStatus(Status status) {
        return this.packageRepository
                .findAllPackagesByStatus(status)
                .stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void packageStatusChange(String id) {
        Package aPackage = this.packageRepository.findById(id);
        this.changeStatus(aPackage);
        this.changeDeliveryDate(aPackage);

        this.packageRepository.updatePackage(aPackage);
    }

    private void changeStatus(Package aPackage) {
        aPackage
                .setStatus(Status.values()[Arrays.asList(Status.values()).indexOf(aPackage.getStatus()) + 1]);
    }

    private void changeDeliveryDate(Package aPackage) {
        long days = (System.currentTimeMillis() % 21) + 20;
        aPackage.setEstimatedDeliveryTime(LocalDateTime.now().plusDays(days));
    }
}
