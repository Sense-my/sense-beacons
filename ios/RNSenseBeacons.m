
#import "RNSenseBeacons.h"

#import <MKBeaconXPlus/Classes/SDK-BXP/MKBXPSDK.h>

@interface RNSenseBeacons() <#superclass#>
//@property (nonatomic, retain) MKBXPCentralManager *manager;
@end

@implementation RNSenseBeacons

/*
- (instancetype)init{
    self = [super init];
    if(self){
        self.manager = [MKBXPCentralManager shared];
    }
}*/

+(BOOL)requiresMainQueueSetup{
    return YES;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(initilize)
{
    
}

RCT_EXPORT_METHOD(setInfo:(NNString *)value)
{
    self.name = value;
}

RCT_EXPORT_METHOD(getInfo:(RCTResponseSenderBlock)callback)
{
    callback(@[self.name]);
}

@end
  
