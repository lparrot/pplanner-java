import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { UiTestComponent } from './pages/ui-test/ui-test.component'
import { LayoutDefaultComponent } from './layouts/layout-default/layout-default.component'
import { LoginComponent } from './pages/login/login.component'

const routes: Routes = [
	{
		path: '', component: LayoutDefaultComponent, children: [
			{ path: 'login', component: LoginComponent },
			{ path: 'ui-test', component: UiTestComponent },
		],
	},
]

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	exports: [ RouterModule ],
})
export class AppRoutingModule {
}
