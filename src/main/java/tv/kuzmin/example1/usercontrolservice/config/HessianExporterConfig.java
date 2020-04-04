package tv.kuzmin.example1.usercontrolservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import tv.kuzmin.example1.usercontrolservice.service.UserService;
import tv.kuzmin.example1.usercontrolservice.service.impl.RemoteAuthServiceImpl;
import tv.kuzmin.userauthcontrolapi.RemoteAuthService;


@Configuration
class HessianExporterConfig
{
    @Bean
    RemoteAuthService remoteAuthService(UserService userService)
    {
        return new RemoteAuthServiceImpl(userService);
    }


    @Bean(name = "/authservice")
    HessianServiceExporter authService(RemoteAuthService remoteAuthService)
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(remoteAuthService);
        exporter.setServiceInterface(RemoteAuthService.class);
        return exporter;
    }
}
