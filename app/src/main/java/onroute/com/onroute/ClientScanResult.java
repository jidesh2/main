package onroute.com.onroute;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ClientScanResult {
    String IpAddr;
    String HWAddr;
    String Device;
    boolean isReachable;
}