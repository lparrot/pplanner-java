import {NgModule} from '@angular/core'
import {CommonModule} from '@angular/common'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import {ReactiveFormsModule} from '@angular/forms'
import {ModalComponent} from './modal/modal.component'
import {ButtonDirective} from './button/button.directive'
import {PortalModule} from '@angular/cdk/portal'
import {MenuOverlayComponent} from './menu-overlay/menu-overlay.component'
import {OverlayModule} from '@angular/cdk/overlay'
import {AlertComponent} from './alert/alert.component'
import {AlertService} from './alert/alert.service'

const components: any[] = [
	AlertComponent,
	ButtonDirective,
	MenuOverlayComponent,
	ModalComponent,
]

const directives: any[] = [
	ButtonDirective,
]

@NgModule({
	imports: [
		CommonModule,
		BrowserAnimationsModule,
		ReactiveFormsModule,
		PortalModule,
		OverlayModule,
	],
	declarations: [
		...components,
		...directives,
	],
	exports: [
		...components,
		...directives,
	],
	providers: [
		AlertService
	]
})
export class UiModule {
}
