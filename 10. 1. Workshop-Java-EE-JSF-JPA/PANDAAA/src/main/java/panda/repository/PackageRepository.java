package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.service.PackageServiceModel;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {

    List<Package> findAllPackagesByStatus(Status status);

    Package updatePackage(Package aPackage);
}
