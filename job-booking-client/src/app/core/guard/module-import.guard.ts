// Guard to prevent importing CoreModule in another module
export function throwIfAlreadyLoaded(parentModule: any, moduleName: string) {
    if (parentModule) {
      throw new Error(
        `${moduleName} has already been loaded. Import ${moduleName} modules in the AppModule only.`
      );
    }
  }
