import { CanActivateFn, CanDeactivateFn, Router } from "@angular/router";
import { RouteService } from "./components/route.service";
import { inject } from "@angular/core";
import { FormComponent } from "./components/form.component";

//(_route, _state)
export const canProceed: CanActivateFn =
    (_route, _state) => {
        const routeSvc = inject(RouteService)
        const router = inject(Router)

        console.info("if can proceed")

        if (routeSvc.proceed)
            return true
        return router.parseUrl('/notice')
        // router path, UrlTree

        // return routeSvc.proceed
    } //boolean, true/false OR UrlTree OR Promise<boolean | UrlTree>, Observable<boolean | UrlTree>

// exit after saved, exit without saved, check formcomp, needs to be reset
export const canLeave: CanDeactivateFn<FormComponent> =
    (comp, _route, _state) => {
        if (!comp.form.dirty)
            return true

        return confirm('you have unsaved data. \nAre you sure you want to leave?')
        // OK = true, UrlTree 
    }

// export const canLeave: CanDeactivateFn<FormComponent> =
// (comp, _route, _state) => {
//     if (!comp.hasProcessed)
//         return true

//     return confirm('you have unsaved data. \nAre you sure you want to leave?')
//     // OK = true  
// }